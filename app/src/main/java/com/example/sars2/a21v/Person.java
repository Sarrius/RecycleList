package com.example.sars2.a21v;

/**
 * Created by sars2 on 10.12.2015.
 */
public class Person {

    String mName;
    String mDescription;

    public Person(String name, String description) {

        this.mName = name;
        this.mDescription = description;

    }

    public void setName(String name){
        this.mName = name;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }
}
