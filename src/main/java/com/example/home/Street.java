package com.example.home;

public class Street {
    private int street_ID;
    private int street_number;
    private String name;

    public Street(){
        street_ID = 0;
        street_number = 0;
        name = "";
    }

    public Street(int street_ID, int street_number, String name) {
        this.street_ID = street_ID;
        this.street_number = street_number;
        this.name = name;
    }

    public int getStreet_ID() {
        return street_ID;
    }

    public void setStreet_ID(int street_ID) {
        this.street_ID = street_ID;
    }

    public int getStreet_number() {
        return street_number;
    }

    public void setStreet_number(int street_number) {
        this.street_number = street_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
