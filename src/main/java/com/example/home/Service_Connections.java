package com.example.home;

public class Service_Connections {
    private int connection_ID;
    private int service_ID;
    private int property_ID;
    private String start_date;
    private String end_date;

    public Service_Connections(){
        connection_ID = 0;
        service_ID = 0;
        property_ID = 0;
        start_date = "";
        end_date = "";
    }

    public Service_Connections(int connection_ID, int service_ID, int property_ID, String start_date, String end_date) {
        this.connection_ID = connection_ID;
        this.service_ID = service_ID;
        this.property_ID = property_ID;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public int getConnection_ID() {
        return connection_ID;
    }

    public void setConnection_ID(int connection_ID) {
        this.connection_ID = connection_ID;
    }

    public int getService_ID() {
        return service_ID;
    }

    public void setService_ID(int service_ID) {
        this.service_ID = service_ID;
    }

    public int getProperty_ID() {
        return property_ID;
    }

    public void setProperty_ID(int property_ID) {
        this.property_ID = property_ID;
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
}
