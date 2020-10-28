package com.java8;

import java.util.Optional;

public class Optional_Basic {

    public static void main(String[] args) {

//※(1/4)創建一個Optional物件
//		Optional<String> optionObj = Optional.empty();                          // Optional.empty();           --> 回傳Optional類別的空實體empty instance
		Optional<String> optionObj = Optional.of("HelloWorld 世界你好");          // Optional.of(obj)            --> 只能接受非null的值，回傳Optional物件 (如果接受值是null，會有NullPointerException) 【測試用 null 或 "HelloWorld 世界你好"】
//        Optional<String> optionObj = Optional.empty();  // Optional.ofNullable(obj)    --> 可以接受null或非null的值，回傳Optional物件 (如果接受值是null，會回傳一個回傳Optional類別的空實體empty instance)【測試用 null 或 "HelloWorld 世界你好"】



//※(2/4)Optional Basic example
        if (optionObj.isPresent())                                              // isPresent()        --> 檢查值是否存在
            System.out.println("內含值不為null時 : " + optionObj.get());            // HelloWorld 世界你好    --> 如果值存在就回傳其值，否則丟出 NoSuchElementException
        else
            System.out.println("內含值為 null時   : " + optionObj);                  // Optional.empty     --> 如果接受值是null，會回傳一個空的empty Optional物件

        optionObj.ifPresent(System.out::println);                               // HelloWorld 世界你好    --> 如果值存在，將該值傳給指定的 consumer lambda 運算式作處理；如果值不存在則什麼都不做
        System.out.println(optionObj.orElseGet(()->"空值"));                     // HelloWorld 世界你好    --> 如果值存在就回傳其值，否則就執行指定的 Supplier lambda 並以其回傳的結果為值(註: 如果想在無值時多做一些事，可以用 orElseGet 來做，它的參數是 Supplier 函數式介面)



//※(3/4)Optional map and flatMap ( map方法中的Function-lambda運算式返回值，可以是任意類型 ; flatMap方法中的Function-lambda運算事返回值，必須是Optionl物件)
        System.out.println("optionObj : " + optionObj                                               .orElseGet(()->"空值")); //HelloWorld 世界你好
        System.out.println("optionObj : " + optionObj.map(str -> str.toUpperCase())                 .orElseGet(()->"空值")); //HELLOWORLD 世界你好
        System.out.println("optionObj : " + optionObj.flatMap(str -> Optional.of(str.toUpperCase())).orElseGet(()->"空值")); //HELLOWORLD 世界你好



//※(4/4)Optional filter ( 如果值存在，則返回一個Optional類別的非空實體 non-empty instance，否則返回一個Optional類別的空實體 empty instance )
		System.out.println("optionObj : " + optionObj                         .filter(str->str.equals("HelloWorld 世界你好")).orElseGet(()->"空值")); //HelloWorld 世界你好
//		System.out.println("optionObj : " + optionObj.map(String::toUpperCase).filter(str->str.equals("HelloWorld 世界你好")).orElseGet(()->"空值")); //空值


    }
}
