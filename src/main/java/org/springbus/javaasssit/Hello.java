package org.springbus.javaasssit;

public class Hello {

    private  String name;

    public void say(){
        System.out.println("hello say");
    }


    public void setName(String name){
       this.name=name;
    }


    public void getName(){
        try {
            System.out.println("hello say");
        }catch (Exception ex){
            System.out.println(ex);
            throw  ex;
        }
    }
}
