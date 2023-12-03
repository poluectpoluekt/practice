package com.example.web.dto;

import com.example.web.models.Person;

import java.math.BigDecimal;

public class InstalmentDTO {

    //private BigDecimal amount; //количество в долях, возможно потом переписать через enum
    private int priceInstallment; // цена рассрочки в долларах
    private int numberOfPayments; // количество в месяцах, 5, 10, 20
    private String emailOwner;
    //private Person owner; думаю, получать почту владельца и искать по ней в БД, и тогда проставить владельца

    //private int idOwner;

//    public int getIdOwner() {
//        return idOwner;
//    }
//
//    public void setIdOwner(int idOwner) {
//        this.idOwner = idOwner;
//    }

//    public BigDecimal getAmount() {
//        return amount;
//    }
//
//    public void setAmount(BigDecimal amount) {
//        this.amount = amount;
//    }

    public int getPriceInstallment() {
        return priceInstallment;
    }

    public void setPriceInstallment(int priceInstallment) {
        this.priceInstallment = priceInstallment;
    }

    public int getNumberOfPayments() {
        return numberOfPayments;
    }

    public void setNumberOfPayments(int numberOfPayments) {
        this.numberOfPayments = numberOfPayments;
    }

    public String getEmailOwner() {
        return emailOwner;
    }

    public void setEmailOwner(String emailOwner) {
        this.emailOwner = emailOwner;
    }


    //public InstalmentDTO() { }


}
