//package com.controller;
//
//
//import com.entity.Product;
//import com.entity.ProductQueryParameter;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.RequestEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import javax.annotation.PostConstruct;
//import java.net.URI;
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * https://medium.com/chikuwa-tech-study/spring-boot-%E7%AC%AC3%E8%AA%B2-controller%E7%9A%84%E4%BD%BF%E7%94%A8-%E4%B8%80-7b76f11d3ee4
// */
//
//@RestController
//@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//public class ProductController_3 {
//
//    //3-1
////    @GetMapping("/products/{id}")
////    public Product getProduct(@PathVariable("id") String id){
////        Product product = new Product();
////        product.setId(id);
////        product.setName("Romantic Story");
////        product.setPrice(200);
////
////        return product;
////
////    }
//
//    //3-2
////    @GetMapping("/products/{id}")
////    public ResponseEntity<Product> getProduct(@PathVariable("id") String id){
////        Product product = new Product();
////        product.setId(request.getId());
////        product.setName(request.getName());
////        product.setPrice(request.getPrice());
////
////        return ResponseEntity.ok().body(product);
////
////    }
//
//    //3-3 四、結合模擬資料庫
//    private final List<Product> productDB = new ArrayList<>();
//
//    //@PostConstruct」標記。每當Controller被建立，該方法會自動執行，新增預設的產品資料。
//    @PostConstruct
//    private void initDB() {
//        productDB.add(new Product("B0001", "Android Development (Java)", 380));
//        productDB.add(new Product("B0002", "Android Development (Kotlin)", 420));
//        productDB.add(new Product("B0003", "Data Structure (Java)", 250));
//        productDB.add(new Product("B0004", "Finance Management", 450));
//        productDB.add(new Product("B0005", "Human Resource Management", 330));
//    }
//
//
////    @GetMapping("/products/{id}")
//    @RequestMapping(value = "/products/{id}" ,method = RequestMethod.GET)
//    public ResponseEntity<Product> getProduct(@PathVariable("id") String id) {
//        Optional<Product> productOp = productDB.stream()
//                .filter(product -> product.getId().equals(id))
//                .findFirst();
//
//        if (!productOp.isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        Product product = productOp.get();
//        return ResponseEntity.ok().body(product);
//    }
//
//    //3-5 五、發送POST請求
////    @PostMapping("/products")
//    @RequestMapping(value = "/products",method = RequestMethod.POST)
//    public ResponseEntity<Product> createProduct(@RequestBody Product request) {
//        //stream() java8新增功能
//        boolean isIdDuplicated = productDB.stream().anyMatch(product -> product.getId().equals(request.getId()));
//
//
//        if (isIdDuplicated) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }
//
//        Product product = new Product();
//        product.setId(request.getId());
//        product.setName(request.getName());
//        product.setPrice(request.getPrice());
//        productDB.add(product);
//
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(product.getId())
//                .toUri();
//
//        /**
//         * fromCurrentRequest：以目前呼叫的資源路徑為基礎來建立URI，此處為「http://…/products」。
//         * path：以目前的資源路徑再做延伸，定義新的路徑格式，此處為「http://…/products/{id}」。
//         * buildAndExpand：將參數填入路徑，產生真實的資源路徑，此處為「http://…/products/實際產品編號」。
//         *
//         */
//
//        return ResponseEntity.created(location).body(product);
//    }
//
//    //4 https://medium.com/chikuwa-tech-study/spring-boot-%E7%AC%AC4%E8%AA%B2-controller%E7%9A%84%E4%BD%BF%E7%94%A8-%E4%BA%8C-b0a38d0af940
//    //4-1 一、發送PUT請求
//
////    @PutMapping("/products/{id}")
//    @RequestMapping(value = "/products/{id}" ,method = RequestMethod.PUT)
//    public ResponseEntity<Product> replaceProduct(@PathVariable("id") String id, @RequestBody Product request) {
//        Optional<Product> productOp = productDB.stream()
//                .filter(product -> product.getId().equals(id))
//                .findFirst();
//
//        if (!productOp.isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Product product = productOp.get();
//        product.setName(request.getName());
//        product.setPrice(request.getPrice());
//
//        return ResponseEntity.ok().body(product);
//    }
//
//    //4-2 二、發送DELETE請求
//
////    @DeleteMapping("/products/{id}")
//    @RequestMapping(value = "/products/{id}",method = RequestMethod.DELETE)
//    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
//        boolean isRemoved = productDB.removeIf(product -> product.getId().equals(id));
//
//        if (isRemoved) {
//            return ResponseEntity.noContent().build();  //204 (No Content) — 沒有內容 。 請求已經成功，且沒有需要回應的內容。
//        } else {
//            return ResponseEntity.notFound().build();   //404 (Not Found) — 未找到。
//        }
//    }
//    //4-3 三、查詢字串
//
////    @GetMapping("/products")
////    public ResponseEntity<List<Product>> getProducts(@RequestParam(value = "keyword",defaultValue = "") String keyword){
////        List<Product> products = productDB.stream()
////                .filter(product -> product.getName().toUpperCase().contains(keyword.toUpperCase()))
////                .collect(Collectors.toList());
////
////        return ResponseEntity.ok().body(products);
////
////    }
//
//    //4-4 四、接收更多查詢字串
//
////    @GetMapping("/products")
//    @RequestMapping(value = "/products" ,method = RequestMethod.GET)
//    public ResponseEntity<List<Product>> getProducts(@ModelAttribute ProductQueryParameter param) {
//        String nameKeyword = param.getKeyword();
//        String orderBy = param.getOrderBy();
//        String sortRule = param.getSortRule();
//
//        Comparator<Product> comparator = Objects.nonNull(orderBy) && Objects.nonNull(sortRule)
//                ? configureSortComparator(orderBy, sortRule)
//                : (p1, p2) -> 0;
//
//        List<Product> products = productDB.stream()
//                .filter(product -> product.getName().toUpperCase().contains(nameKeyword.toUpperCase()))
//                .sorted(comparator)
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok().body(products);
//
//
//    }
//
//    private Comparator<Product> configureSortComparator(String orderBy, String sortRule) {
//        Comparator<Product> comparator = (p1, p2) -> 0; //預設接受p1,p2參數，return 0
//
//        if (orderBy.equalsIgnoreCase("price")) {
//
//            comparator = Comparator.comparing(Product::getPrice); //:: java8 類名::方法名。 Function<T,R> T表示傳入類型，R表示返回類型。 Product傳入類型，getPrice返回類型(getPrice 方法名)
////            comparator = Comparator.comparing(product -> product.getPrice());     //這兩種寫法都一樣
//
//        } else if (orderBy.equalsIgnoreCase("name")) {
//            comparator = Comparator.comparing(Product::getName);
//        }
//
//        if (sortRule.equalsIgnoreCase("desc")) {
//            comparator = comparator.reversed();
//        }
//        return comparator;
//    }
//}
//
//
