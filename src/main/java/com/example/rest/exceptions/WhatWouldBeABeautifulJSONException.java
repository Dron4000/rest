package com.example.rest.exceptions;

import org.springframework.http.HttpStatus;

public class WhatWouldBeABeautifulJSONException {// тип ответственный за json
    private  String info;


    public WhatWouldBeABeautifulJSONException() {

    }


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;

    }


}
