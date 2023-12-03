package com.example.web.dto;


import java.math.BigDecimal;

public class PersonDTO { //добавить поля сущности, чтобы json можно было получать эти поля

    private String name;
    private String email;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public BigDecimal getBalance() {
//        return balance;
//    }
//
//    public void setBalance(BigDecimal balance) {
//        this.balance = balance;
//    }
//
//    public String getWallet() {
//        return wallet;
//    }
//
//    public void setWallet(String wallet) {
//        this.wallet = wallet;
//    }


    @Override
    public String toString() {
        return "PersonDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
