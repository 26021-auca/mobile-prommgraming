package com.example.quiz_four;

import java.util.Date;

/**
 * Model class representing a Customer.
 */
public class Customer {
    private int customerID;
    private String fullNames;
    private String gender;
    private Date birthDate;
    private String nationality;
    private String customerNID;

    // Default constructor
    public Customer() {}

    // Getter for customerID
    public int getCustomerID() {
        return customerID;
    }

    // Setter for customerID
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    // Getter for fullNames
    public String getFullNames() {
        return fullNames;
    }

    // Setter for fullNames
    public void setFullNames(String fullNames) {
        this.fullNames = fullNames;
    }

    // Getter for gender
    public String getGender() {
        return gender;
    }

    // Setter for gender
    public void setGender(String gender) {
        this.gender = gender;
    }

    // Getter for birthDate
    public Date getBirthDate() {
        return birthDate;
    }

    // Setter for birthDate
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    // Getter for nationality
    public String getNationality() {
        return nationality;
    }

    // Setter for nationality
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    // Getter for customerNID
    public String getCustomerNID() {
        return customerNID;
    }

    // Setter for customerNID
    public void setCustomerNID(String customerNID) {
        this.customerNID = customerNID;
    }
}
