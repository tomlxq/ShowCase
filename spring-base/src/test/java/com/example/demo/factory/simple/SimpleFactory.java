package com.example.demo.factory.simple;

public class SimpleFactory {
   public Car getCar(String name){
       if("Audi".equals(name)){
           return new Audi();
       }else if("Benz".equals(name)){
           return new Benz();
       }
       else if("BWN".equals(name)){
           return new BWN();
       }else{
           System.out.println("没有这样的产品！");
           return null;
       }
   }
}
