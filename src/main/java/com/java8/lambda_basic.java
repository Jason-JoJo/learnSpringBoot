package com.java8;

import java.util.function.Consumer;
import java.util.*;

/* Lambda 運算式
 * Lambdas(也稱為closures閉包)是整個 Java 8 中最大的語言變化
 * Java 8 的 Lambda 允許我們『傳遞一段程式碼』給某個method『視為方法的參數』，也就是『將這段程式碼變成某個類別物件的某個method的實作內容』
	1) 事實上這是每個函數式程式語言 (Functional Programming Language，簡稱 FP）開發人員，都非常熟悉的概念，如 Haskel，Scala，...等
	2) 在Java 8以前，Java開發人員是使用匿名內部類別(anonymous classes)模板來替代Lambda運算式
	3) Lambda 源自於一種古老的演算 ── λ演算（Lambda正是拉丁字母λ的讀音）。在λ演算中，函數是匿名的，代表著函數的名稱並不重要，因此，將『傳遞一段程式碼』的功能取名為Lambda運算式

 * Lambda運算式的使用需要藉助於函數式介面(Functional Interfaces)，也就是說只有函數式介面出現地方，才可以運用lambda運算式
    1) 所有Java的lambda，在語義上只是函數式介面實作的另一種方式(簡化程式碼的方式)
    2) Lambda expression 的樣式： 參數 -> 運算式
*/

public class lambda_basic {


        public static void main(String[] args) {

        List<String> myList = new ArrayList<String>();
        myList.add("Hello2");
        myList.add("Hello1");

        Collections.sort(myList);  //排序

        System.out.println("\n傳統for迴圈");
        for (int i = 0; i < myList.size(); i++)
            System.out.println(myList.get(i)); // (注意:Set不能用傳統的for迴圈)

        System.out.println("\n使用iterator迴圈");
        for (Iterator<String> it = myList.iterator(); it.hasNext();)
            System.out.println(it.next() + " ");

        System.out.println("\nJDK 1.5 增強型for迴圈 - Enhanced for Loop");
        for (String data : myList)
            System.out.println(data);

//        System.out.println("lambda ");
//        myList.forEach(s -> System.out.println(s));



		/*
		 * Lambda expression 的樣式： 參數 -> 運算式
		 * 參數的部份：
			1) (型別 變數x, 型別 變數y) ：表示方法需要傳入兩個參數，並同時指定型別： 例如：(int x, int y) ->
			2) (   變數x,    變數y)  ： 表示方法需要傳入兩個參數，並同時省略型別： 例如：(x, y) ->

			3) (型別 變數x)：表示方法需要傳入一個參數x， 並指定型別：             例如：(int x) ->
			4) (   變數x)： 表示方法需要傳入一個參數x， 並省略型別：             例如：    (x) ->
			5)     變數x ： 表示方法需要傳入一個參數x， 並省略型別與小括號()：     例如：     x  ->
			6)        ()： 表示方法不需傳入參數：                            例如：     () ->
			7) 註: 只有單一參數，並省略型別時，可不需小括號()，其它需小括號()

		 * 運算式的部份：
			1) {...}：多行敘述時，必須有大括號{}，若要回傳值必須加 return
			2) {return x+y;}：有回傳值，只有一行敘述，但有撰寫return時，則大括號{}不可省略
			3)         x+y  ：有回傳值，只有一行敘述，Java會自動回傳值，因此可省略return的撰寫，但大括號{}必須省略 (註:編譯器會根據上下文推斷返回值的類型，因此可省略return關鍵字)
			4) System.out.println(x+y); 只有一行敘述，無回傳值，則大括號{}可省略
			5) {}：表示不執行任何事
			6) 註: 只有單一行敘述，且無回傳值(或有回傳值，但省略return)時，可不需大括號{}，其它需大括號{}
		*/


		/*
		 * Java 8 新加入的方法參考(Method References)，是 lambda 運算式的一種，當你的 lambda 表示式呼叫一個既有的方法時，就可以使用 Method References，使用方式如下
		 * 方法參考(Method References)語法格式有以下四種：
            1) objectName:: instanceMethod   物件名稱::成員方法名稱   ("世界你好"::length)  (System.out::println) (new Random()::nextDouble)
		    2) ClassName :: instanceMethod   類別名稱::成員方法名稱   (String::isEmpty)   (String::length)      (Reference to an instance method of a arbitrary object supplied later)
		    3) ClassName :: staticMethod     類別名稱::類別方法名稱   (String::valueOf)   (Math::random)
		    4) ClassName :: new              類別名稱::new        (String::new)
		*/


        /*
         * 在 Java 8 中，定義interface時，可以加入預設實作，或者稱為預設方法（Default methods）
         * 預設方法的實例之一，就是定義在java.lang.Iterable介面的forEach方法
         * forEach方法 : default void forEach ( Consumer <? super?T>? action )
         * 應用例如          : myCollection.forEach ( e -> System.out.println(e) );
         */

        System.out.println("\nJDK 1.8 Lambda expression - 以下六種寫法");

        Consumer<String> c = new Consumer<String>(){
            @Override
            public void accept(String data) {
                System.out.println(data);
            }
        };
        System.out.println("\n1.)");
        myList.forEach(c);
        System.out.println("\n2.)");
        myList.forEach( (String data) -> {
                    System.out.println(data);
                }
        );
        System.out.println("\n3.)");
        myList.forEach( (String data) ->  System.out.println(data) );
        System.out.println("\n4.)");
        myList.forEach(        (data) ->  System.out.println(data) );
        System.out.println("\n5.)");
        myList.forEach(         data  ->  System.out.println(data) );
        System.out.println("\n6.)");
        myList.forEach(System.out::println); //方法參考(Method References)

    }

    }
