package com.example.home;

public class Staff {
    private int staff_ID;
    private String First_Name;
    private String last_name;
    private int CNIC;
    private int phone_number;
    private String designation;
    private int office_no;
    private int team_ID;

    public Staff(){
        staff_ID = 0;
        First_Name = "";
        last_name = "";
        CNIC = 0;
        phone_number = 0;
        designation = "";
        office_no = 0;
        team_ID = 0;
    }

    public Staff(int staff_ID, String first_Name, String last_name, int CNIC, int phone_number, String designation, int office_no, int team_ID) {
        this.staff_ID = staff_ID;
        First_Name = first_Name;
        this.last_name = last_name;
        this.CNIC = CNIC;
        this.phone_number = phone_number;
        this.designation = designation;
        this.office_no = office_no;
        this.team_ID = team_ID;
    }

    public int getStaff_ID() {
        return staff_ID;
    }

    public void setStaff_ID(int staff_ID) {
        this.staff_ID = staff_ID;
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

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getOffice_no() {
        return office_no;
    }

    public void setOffice_no(int office_no) {
        this.office_no = office_no;
    }

    public int getTeam_ID() {
        return team_ID;
    }

    public void setTeam_ID(int team_ID) {
        this.team_ID = team_ID;
    }
}
