package com.example.home;

public class Block {
    private int Block_ID;
    private String Block_Name;
    private int No_ofProperties;
    private int size;

    public Block(){
        Block_ID = 0;
        Block_Name = "";
        No_ofProperties = 0;
        size = 0;
    }

    public Block(int Block_ID, String Block_Name, int No_ofProperties, int size){
        this.Block_ID = Block_ID;
        this.Block_Name = Block_Name;
        this.No_ofProperties = No_ofProperties;
        this.size = size;
    }

    public int getBlock_ID() {
        return Block_ID;
    }

    public void setBlock_ID(int block_ID) {
        Block_ID = block_ID;
    }

    public String getBlock_Name() {
        return Block_Name;
    }

    public void setBlock_Name(String block_Name) {
        Block_Name = block_Name;
    }

    public int getNo_ofProperties() {
        return No_ofProperties;
    }

    public void setNo_ofProperties(int no_ofProperties) {
        No_ofProperties = no_ofProperties;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
