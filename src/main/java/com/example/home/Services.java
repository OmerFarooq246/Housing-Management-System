package com.example.home;

public class Services {
    private int service_ID;
    private String type;
    private int no_ofConnections;

    public Services(){
        service_ID = 0;
        type = "";
        no_ofConnections = 0;
    }

    public Services(int service_ID, String type, int no_ofConnections){
        this.service_ID = service_ID;
        this.type = type;
        this.no_ofConnections = no_ofConnections;
    }

    public int getService_ID() {
        return service_ID;
    }

    public void setService_ID(int service_ID) {
        this.service_ID = service_ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNo_ofConnections() {
        return no_ofConnections;
    }

    public void setNo_ofConnections(int no_ofConnections) {
        this.no_ofConnections = no_ofConnections;
    }
}
