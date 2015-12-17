package com.example.sars2.RecycleList;

/**
 * Created by sars2 on 17.12.2015.
 */
public class GeneralPath {
    private String path;

    private static GeneralPath ourInstance = new GeneralPath();

    public static GeneralPath getInstance() {
        return ourInstance;
    }

    private GeneralPath() {
        path = "";
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
