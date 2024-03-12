package com.example.home;

public class Contracts {
    private int contract_ID;
    private int contractor_ID;
    private String start_date;
    private String end_date;
    private int total_amount;
    private int amount_received;
    private int amount_remaining;
    private String type;

    public Contracts(){
        contract_ID = 0;
        contractor_ID = 0;
        start_date = "";
        end_date = "";
        total_amount = 0;
        amount_received = 0;
        amount_remaining = 0;
        type = "";
    }

    public Contracts(int contract_ID, int contractor_ID, String start_date, String end_date, int total_amount, int amount_received, int amount_remaining, String type) {
        this.contract_ID = contract_ID;
        this.contractor_ID = contractor_ID;
        this.start_date = start_date;
        this.end_date = end_date;
        this.total_amount = total_amount;
        this.amount_received = amount_received;
        this.amount_remaining = amount_remaining;
        this.type = type;
    }

    public int getContract_ID() {
        return contract_ID;
    }

    public void setContract_ID(int contract_ID) {
        this.contract_ID = contract_ID;
    }

    public int getContractor_ID() {
        return contractor_ID;
    }

    public void setContractor_ID(int contractor_ID) {
        this.contractor_ID = contractor_ID;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
