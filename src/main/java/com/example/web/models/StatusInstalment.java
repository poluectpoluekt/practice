package com.example.web.models;

public enum StatusInstalment {
    ACTIVE("active"), PAID("paid"), ANNULED("annuled"), OVERDUE("overdue");

    private String value;

    private StatusInstalment(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
