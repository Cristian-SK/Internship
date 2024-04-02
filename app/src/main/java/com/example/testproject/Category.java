package com.example.testproject;

public class Category {

    private String id;
    private String name;
    private String description;


    public Category(String id,String name, String description){

        this.id = id;
        this.name = name;
        this.description = description;

    }
    public String getCatId(){
        return id;
    }
    public String getCatName(){
        return name;
    }
    public String getCatDescription(){
        return description;
    }
}
