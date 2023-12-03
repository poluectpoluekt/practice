package com.example.web.models;

//import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Instalment", schema = "private")
public class Instalment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "amount")
    private BigDecimal amount; // в долях
    @Column(name = "price_installment")
    private int priceInstallment; // стоимость в USD
    @Column(name = "number_of_payments")
    private int numberOfPayments;
    @Column(name = "date_of_start")
    private LocalDateTime dateOfStart;
    @Column(name = "instalment_periods")
    private int instalmentPeriods;
    @Column(name = "rate_installment")
    private Double rateInstallment;
    @Column(name = "bonus_percentage") //подумать как писать название через нижний _
    private int bonusPercentage; // подумать, как сделать лист
    @Column(name = "status")
    private String status;
    //@Column(name = "owner")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_email", referencedColumnName = "email") //возможно, переименовать name в owner_email
    private Person owner; // тут может другой тип String

    public Instalment() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

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

    public LocalDateTime getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(LocalDateTime dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public int getInstalmentPeriods() {
        return instalmentPeriods;
    }

    public void setInstalmentPeriods(int instalmentPeriods) {
        this.instalmentPeriods = instalmentPeriods;
    }

    public Double getRateInstallment() {
        return rateInstallment;
    }

    public void setRateInstallment(Double rateInstallment) {
        this.rateInstallment = rateInstallment;
    }

    public int getBonusPercentage() {
        return bonusPercentage;
    }

    public void setBonusPercentage(int bonusPercentage) {
        this.bonusPercentage = bonusPercentage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Instalment{" +
                "amount=" + amount +
                ", priceInstallment=" + priceInstallment +
                ", numberOfPayments=" + numberOfPayments +
                ", dateOfStart=" + dateOfStart +
                ", instalmentPeriods=" + instalmentPeriods +
                ", rateInstallment=" + rateInstallment +
                ", bonusPercentage=" + bonusPercentage +
                ", status='" + status + '\'' +
                ", owner=" + owner +
                '}';
    }
}
