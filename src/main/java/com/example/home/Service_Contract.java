package com.example.home;

public class Service_Contract {
    private int contract_ID;
    private int service_ID;
    private String company;
    private String start_date;
    private String end_date;
    private int amount;

    public Service_Contract(){
        contract_ID = 0;
        service_ID = 0;
        company = "";
        start_date = "";
        end_date = "";
        amount = 0;
    }

    public Service_Contract(int contract_ID, int service_ID, String company, String start_date, String end_date, String duration, int amount) {
        this.contract_ID = contract_ID;
        this.service_ID = service_ID;
        this.company = company;
        this.start_date = start_date;
        this.end_date = end_date;
        this.amount = amount;
    }

    public int getContract_ID() {
        return contract_ID;
    }

    public void setContract_ID(int contract_ID) {
        this.contract_ID = contract_ID;
    }

    public int getService_ID() {
        return service_ID;
    }

    public void setService_ID(int service_ID) {
        this.service_ID = service_ID;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
