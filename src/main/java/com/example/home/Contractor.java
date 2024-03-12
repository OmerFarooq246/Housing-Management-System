package com.example.home;

public class Contractor {
    private int contractor_ID;
    private String name;
    private String type;

    public Contractor(){
        contractor_ID = 0;
        name = "";
        type = "";
    }

    public Contractor(int contractor_ID, String name, String type) {
        this.contractor_ID = contractor_ID;
        this.name = name;
        this.type = type;
    }

    public int getContractor_ID() {
        return contractor_ID;
    }

    public void setContractor_ID(int contractor_ID) {
        this.contractor_ID = contractor_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
