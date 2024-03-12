package com.example.home;

public class Suspects {
    private int suspect_ID;
    private int crime_ID;
    private String name;
    private String allegations;

    public Suspects(){
        suspect_ID = 0;
        crime_ID = 0;
        name = "";
        allegations = "";
    }

    public Suspects(int suspect_ID, int crime_ID, String name, String allegations) {
        this.suspect_ID = suspect_ID;
        this.crime_ID = crime_ID;
        this.name = name;
        this.allegations = allegations;
    }

    public int getSuspect_ID() {
        return suspect_ID;
    }

    public void setSuspect_ID(int suspect_ID) {
        this.suspect_ID = suspect_ID;
    }

    public int getCrime_ID() {
        return crime_ID;
    }

    public void setCrime_ID(int crime_ID) {
        this.crime_ID = crime_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAllegations() {
        return allegations;
    }

    public void setAllegations(String allegations) {
        this.allegations = allegations;
    }
}
