package com.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class forEach_on_Collection {

    public static void main(String[] args) {

//		※創建List (Java 8 之前的寫法)
        List<String> myCollection = Arrays.asList("5", "2", "3", "4", "1", "Hello", "Hello");

//		※創建List (Java 8 的Stream/Lambda寫法)
//		List<String> myCollection = Stream.of("5", "2", "3", "4", "1", "Hello", "Hello").collect(Collectors.toList());

//		※創建Set  (Java 8 的Stream/Lambda寫法)
//		Set<String>  myCollection = Stream.of("5", "2", "3", "4", "1", "Hello", "Hello").collect(Collectors.toSet());



//		※擷取Collection集合中的元素 (Java 8 之前的forEach寫法)
        for (String data : myCollection) {
            System.out.println(data);
        }
//		※測試內含值是否存在 (Java 8 之前的寫法)
        System.out.println("※contains=" + myCollection.contains("3"));



        System.out.println();



//		※擷取Collection集合中的元素  (Java 8 的Lambda寫法)
        myCollection.forEach(data -> System.out.println(data)); // (參數 -> 運算式)
        myCollection.forEach(System.out::println);              // 方法參考(Method References)

//		※轍測試內含值是否存在  (Java 8 的Stream/Lambda寫法)
        System.out.println("※contains=" + myCollection.stream().anyMatch("3"::equals));              // (類別名稱::方法名稱)
        System.out.println("※contains=" + Stream.of("5", "2", "3", "4", "1").anyMatch("3"::equals)); // (類別名稱::方法名稱)

    }

}
