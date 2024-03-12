package com.example.home;

import javafx.beans.property.SimpleStringProperty;


public class Crime {
    private int crime_ID;
    private String type;
    private String date;
    private String resolve_date;
    private String status;
    private int block_ID;
    private int street_ID;
    private int property_ID;

    public Crime(){
        crime_ID = 0;
        type = "";
        date = "";
        resolve_date = "";
        status = "";
        block_ID = 0;
        street_ID = 0;
        property_ID = 0;
    }

    public Crime(int crime_ID, String type, String date, String resolve_date, String status, int block_ID, int street_ID, int property_ID) {
        this.crime_ID = crime_ID;
        this.type = type;
        this.date = date;
        this.resolve_date = resolve_date;
        this.status = status;
        this.block_ID = block_ID;
        this.street_ID = street_ID;
        this.property_ID = property_ID;
    }

    public int getCrime_ID() {
        return crime_ID;
    }

    public void setCrime_ID(int crime_ID) {
        this.crime_ID = crime_ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResolve_date() {
        return resolve_date;
    }

    public void setResolve_date(String resolve_date) {
        this.resolve_date = resolve_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBlock_ID() {
        return block_ID;
    }

    public void setBlock_ID(int block_ID) {
        this.block_ID = block_ID;
    }

    public int getStreet_ID() {
        return street_ID;
    }

    public void setStreet_ID(int street_ID) {
        this.street_ID = street_ID;
    }

    public int getProperty_ID() {
        return property_ID;
    }

    public void setProperty_ID(int property_ID) {
        this.property_ID = property_ID;
    }
}
