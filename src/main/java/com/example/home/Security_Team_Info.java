package com.example.home;

public class Security_Team_Info {
    private int team_ID;
    private int block_ID;
    private int no_ofMembers;
    private String task;
    private String status;

    public Security_Team_Info(){
        team_ID = 0;
        block_ID = 0;
        no_ofMembers = 0;
        task = "";
        status = "";
    }

    public Security_Team_Info(int team_ID, int block_ID, int no_ofMembers, String task, String status) {
        this.team_ID = team_ID;
        this.block_ID = block_ID;
        this.no_ofMembers = no_ofMembers;
        this.task = task;
        this.status = status;
    }

    public int getTeam_ID() {
        return team_ID;
    }

    public void setTeam_ID(int team_ID) {
        this.team_ID = team_ID;
    }

    public int getBlock_ID() {
        return block_ID;
    }

    public void setBlock_ID(int block_ID) {
        this.block_ID = block_ID;
    }

    public int getNo_ofMembers() {
        return no_ofMembers;
    }

    public void setNo_ofMembers(int no_ofMembers) {
        this.no_ofMembers = no_ofMembers;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
