package com.example.home;

public class Paid_Installments {
    private int installment_ID;
    private int purchase_ID;
    private int installment_no;
    private int amount;
    private String date;

    public Paid_Installments(){
        installment_ID = 0;
        purchase_ID = 0;
        installment_no = 0;
        amount = 0;
        date = "";
    }

    public Paid_Installments(int installment_ID, int purchase_ID, int installment_no, int amount, String date){
        this.installment_ID = installment_ID;
        this.purchase_ID = purchase_ID;
        this.installment_no = installment_no;
        this.amount = amount;
        this.date = date;
    }

    public int getInstallment_ID() {
        return installment_ID;
    }

    public void setInstallment_ID(int installment_ID) {
        this.installment_ID = installment_ID;
    }

    public int getPurchase_ID() {
        return purchase_ID;
    }

    public void setPurchase_ID(int purchase_ID) {
        this.purchase_ID = purchase_ID;
    }

    public int getInstallment_no() {
        return installment_no;
    }

    public void setInstallment_no(int installment_no) {
        this.installment_no = installment_no;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
