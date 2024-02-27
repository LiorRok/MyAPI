package com.example.myapi.models;

import java.util.List;

public class States {

    private String name;
    private String nativeName;
    private String flag;
    private List<String> borders = null;

    public States(String name, String nativeName, String flag, List<String> borders) {
        this.name = name;
        this.nativeName = nativeName;
        this.flag = flag;
        this.borders = borders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }
}
