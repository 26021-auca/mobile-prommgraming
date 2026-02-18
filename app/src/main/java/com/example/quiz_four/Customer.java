package com.example.quiz_four;

import java.util.Date;

public class Customer {
    private int customerID;
    private String fullNames;
    private String gender;
    private Date birthDate;
    private String nationality;
    private String customerNID;

    public Customer() {
    }

    public Customer(int customerID, String fullNames, String gender, Date birthDate, String nationality, String customerNID) {
        this.customerID = customerID;
        this.fullNames = fullNames;
        this.gender = gender;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.customerNID = customerNID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFullNames() {
        return fullNames;
    }

    public void setFullNames(String fullNames) {
        this.fullNames = fullNames;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCustomerNID() {
        return customerNID;
    }

    public void setCustomerNID(String customerNID) {
        this.customerNID = customerNID;
    }
}
