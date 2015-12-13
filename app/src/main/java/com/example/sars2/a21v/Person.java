package com.example.sars2.a21v;

/**
 * Created by sars2 on 10.12.2015.
 */
public class Person {

    String name;
    String description;

    public Person(String name, String description) {
        this.name = name;
        this.description = description;

    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
