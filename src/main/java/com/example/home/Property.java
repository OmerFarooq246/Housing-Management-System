package com.example.home;

public class Property {
    private int Property_ID;
    private int Owner_ID;
    private int Street_ID;
    private int Block_ID;
    private String type;
    private int length;
    private int width;
    private int floors;
    private int size;

    public Property(){
        Property_ID = 0;
        Owner_ID = 0;
        Street_ID = 0;
        Block_ID = 0;
        type = "";
        length = 0;
        width = 0;
        floors = 0;
        size = 0;
    }

    public Property(int Property_ID, int Owner_ID, int Street_ID, int Block_ID, String type, int length, int width, int floors, int size){
        this.Property_ID = Property_ID;
        this.Owner_ID = Owner_ID;
        this.Street_ID = Street_ID;
        this.Block_ID = Block_ID;
        this.type = type;
        this.length = length;
        this.width = width;
        this.floors = floors;
        this.size = size;
    }

    public int getProperty_ID() {
        return Property_ID;
    }

    public void setProperty_ID(int property_ID) {
        Property_ID = property_ID;
    }

    public int getOwner_ID() {
        return Owner_ID;
    }

    public void setOwner_ID(int owner_ID) {
        Owner_ID = owner_ID;
    }

    public int getStreet_ID() {
        return Street_ID;
    }

    public void setStreet_ID(int street_ID) {
        Street_ID = street_ID;
    }

    public int getBlock_ID() {
        return Block_ID;
    }

    public void setBlock_ID(int block_ID) {
        Block_ID = block_ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
