package com.dao;

import com.entity.Product;
import com.entity.ProductQueryParameter;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MockProductDAO {

    private List<Product> productDB = new ArrayList<>();

    public Product inser(Product product){
        productDB.add(product);
        return product;
    }

    public Product replace(String id,Product product){
        Optional<Product> productOp = find(id);
        productOp.ifPresent(p -> {
            p.setName(product.getName());
            p.setPrice(product.getPrice());
        });
        return product;
    }

    public void delete(String id){
        productDB.removeIf(product -> product.getId().equals(id));
    }

    public Optional<Product> find(String id){

        return productDB.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    public List<Product> find(ProductQueryParameter param){
        String nameKeyword = Optional.ofNullable(param.getKeyword()).orElse(""); //如果找不到就回傳 ""，而不是null
        String orderBy = param.getOrderBy();
        String sortRule = param.getSortRule();

        Comparator<Product> comparator = Objects.nonNull(orderBy) && Objects.nonNull(sortRule)
                ? configurSortComparator(orderBy,sortRule)
                : (p1,p2) ->0;

        return productDB.stream()
                .filter(product -> product.getName().contains(nameKeyword))
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    private Comparator<Product> configurSortComparator(String orderBy,String sortRule){
        Comparator comparator = (p1,p2) -> 0;

        if(orderBy.equalsIgnoreCase("price")){
            comparator = Comparator.comparing(Product::getPrice);
        }else if(orderBy.equalsIgnoreCase("name")){
            comparator = Comparator.comparing(Product::getName);
        }

        if(sortRule.equalsIgnoreCase("desc")){
            comparator = comparator.reversed();
        }
        return comparator;
    }
}
