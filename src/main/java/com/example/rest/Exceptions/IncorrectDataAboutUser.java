package com.example.rest.Exceptions;

public class IncorrectDataAboutUser {// тип ответственный за json
    private  String info;

    public IncorrectDataAboutUser() {

    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
