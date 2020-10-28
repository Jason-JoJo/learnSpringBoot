package com.service;

import com.dao.MockProductDAO;
import com.entity.Product;
import com.entity.ProductQueryParameter;
import com.exception.ConflictException;
import com.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private MockProductDAO productDAO; //使用@Autowired標記的全域變數，其資料型態必須是有添加特定標記的類別，

    public Product createProduct(Product request){
        boolean isIdDuplicated = productDAO.find(request.getId()).isPresent();
        if(isIdDuplicated){
            throw new ConflictException("The id of the product is duplicated.");
        }
        Product product = new Product();
        product.setId(request.getId());
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        return productDAO.inser(product);
    }

    public Product replaceProduct(String id,Product request){
        Product product = getProduct(id);

        return productDAO.replace(product.getId(),request);
    }

    public void delecteProduct(String id){
        Product product = getProduct(id);
        productDAO.delete(product.getId());

    }

    public Product getProduct(String id){
        return productDAO.find(id)
                .orElseThrow(
                        ()-> new NotFoundException("Can't find product.")
                );
    }

    public List<Product> getProducts(ProductQueryParameter param){

        return productDAO.find(param);
    }
}
