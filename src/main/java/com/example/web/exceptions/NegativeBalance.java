package com.example.web.exceptions;

public class NegativeBalance extends Exception{

    public NegativeBalance(String message){
        super(message);
    }
}
