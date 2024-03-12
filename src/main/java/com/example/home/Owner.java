package com.example.home;

public class Owner {
    private int Owner_ID;
    private String First_Name;
    private String last_name;
    private int CNIC;
    private String address;
    private int phone_number;
    private String profession;
    private String city;
    private int No_ofProperties;

    public Owner(){
        Owner_ID = 0;
        First_Name = "";
        last_name = "";
        CNIC = 0;
        address = "";
        phone_number = 0;
        profession = "";
        city = "";
        No_ofProperties = 0;
    }

    public Owner(int Owner_ID, String First_Name, String last_name, int CNIC, String address, int phone_number, String profession, String city, int No_ofProperties){
        this.Owner_ID = Owner_ID;
        this.First_Name = First_Name;
        this.last_name = last_name;
        this.CNIC = CNIC;
        this.address = address;
        this.phone_number = phone_number;
        this.profession = profession;
        this.city = city;
        this.No_ofProperties = No_ofProperties;
    }

    public int getOwner_ID() {
        return Owner_ID;
    }

    public void setOwner_ID(int owner_ID) {
        Owner_ID = owner_ID;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getCNIC() {
        return CNIC;
    }

    public void setCNIC(int CNIC) {
        this.CNIC = CNIC;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNo_ofProperties() {
        return No_ofProperties;
    }

    public void setNo_ofProperties(int no_ofProperties) {
        No_ofProperties = no_ofProperties;
    }
}

