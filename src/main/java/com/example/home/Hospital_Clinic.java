package com.example.home;

public class Hospital_Clinic {
    private int hospital_ID;
    private int property_ID;
    private String name;
    private String type;
    private String emergency;
    private String ambulance;

    public Hospital_Clinic(){
        hospital_ID = 0;
        property_ID = 0;
        name = "";
        type = "";
        emergency = "";
        ambulance = "";
    }

    public Hospital_Clinic(int hospital_ID, int property_ID, String name, String type, String emergency, String ambulance){
        this.hospital_ID = hospital_ID;
        this.property_ID = property_ID;
        this.name = name;
        this.type = type;
        this.emergency = emergency;
        this.ambulance = ambulance;
    }

    public int getHospital_ID() {
        return hospital_ID;
    }

    public void setHospital_ID(int hospital_ID) {
        this.hospital_ID = hospital_ID;
    }

    public int getProperty_ID() {
        return property_ID;
    }

    public void setProperty_ID(int property_ID) {
        this.property_ID = property_ID;
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

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public String getAmbulance() {
        return ambulance;
    }

    public void setAmbulance(String ambulance) {
        this.ambulance = ambulance;
    }
}
