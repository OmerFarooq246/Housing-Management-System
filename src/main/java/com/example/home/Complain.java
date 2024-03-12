package com.example.home;

public class Complain {
    private int Complain_ID;
    private String Name;
    private int Phone_Number;
    private int House_ID;
    private String Complain;
    private String Time;
    private String Status;

    public Complain(){
        Complain_ID = 0;
        Name = "";
        Phone_Number = 0;
        House_ID = 0;
        Complain = "";
        Time = "";
        Status = "";
    }

    public Complain(int Complain_ID, String Name, int Phone_Number, int House_ID, String Complain, String Time, String Status){
        this.Complain_ID = 0;
        this.Name = "";
        this.Phone_Number = 0;
        this.House_ID = 0;
        this.Complain = "";
        this.Time = "";
        this.Status = "";
    }

    public int getComplain_ID() {
        return Complain_ID;
    }

    public void setComplain_ID(int complain_ID) {
        Complain_ID = complain_ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(int phone_Number) {
        Phone_Number = phone_Number;
    }

    public int getHouse_ID() {
        return House_ID;
    }

    public void setHouse_ID(int house_ID) {
        House_ID = house_ID;
    }

    public String getComplain() {
        return Complain;
    }

    public void setComplain(String complain) {
        Complain = complain;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}

