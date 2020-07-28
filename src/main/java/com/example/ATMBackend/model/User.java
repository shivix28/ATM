package com.example.ATMBackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String userID;
    private String PIN;
    private int amount;


    public User(@JsonProperty("id") String userID,
                @JsonProperty("pin") String pin,
                @JsonProperty("amount") int amount) {
        this.userID = userID;
        this.PIN = pin;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public String getPIN() {
        return PIN;
    }

    public String getUserID() {
        return userID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
