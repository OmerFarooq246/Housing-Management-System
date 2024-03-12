package com.example.home;

public class Property_Purchases {
    private int purchase_ID;
    private int customer_ID;
    private int property_ID;
    private String date;
    private int total_amount;
    private int amount_received;
    private int amount_remaining;
    private int no_ofInstallments;

    public Property_Purchases(){
        purchase_ID = 0;
        customer_ID = 0;
        property_ID = 0;
        date= "";
        total_amount = 0;
        amount_received = 0;
        amount_remaining = 0;
        no_ofInstallments = 0;
    }

    public Property_Purchases(int purchase_ID, int customer_ID, int property_ID, String date, int total_amount, int amount_received, int amount_remaining, int no_ofInstallments){
        this.purchase_ID = purchase_ID;
        this.customer_ID = customer_ID;
        this.property_ID = property_ID;
        this.date = date;
        this.total_amount = total_amount;
        this.amount_received = amount_received;
        this.amount_remaining = amount_remaining;
        this.no_ofInstallments = no_ofInstallments;
    }

    public int getPurchase_ID() {
        return purchase_ID;
    }

    public void setPurchase_ID(int purchase_ID) {
        this.purchase_ID = purchase_ID;
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    public int getProperty_ID() {
        return property_ID;
    }

    public void setProperty_ID(int property_ID) {
        this.property_ID = property_ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public int getAmount_received() {
        return amount_received;
    }

    public void setAmount_received(int amount_received) {
        this.amount_received = amount_received;
    }

    public int getAmount_remaining() {
        return amount_remaining;
    }

    public void setAmount_remaining(int amount_remaining) {
        this.amount_remaining = amount_remaining;
    }

    public int getNo_ofInstallments() {
        return no_ofInstallments;
    }

    public void setNo_ofInstallments(int no_ofInstallments) {
        this.no_ofInstallments = no_ofInstallments;
    }
}
