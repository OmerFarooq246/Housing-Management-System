package com.example.home;

public class Team_on_Crime {
    private int crime_ID;
    private int team_ID;

    public Team_on_Crime(){
        crime_ID = 0;
        team_ID = 0;
    }

    public Team_on_Crime(int crime_ID, int team_ID) {
        this.crime_ID = crime_ID;
        this.team_ID = team_ID;
    }

    public int getCrime_ID() {
        return crime_ID;
    }

    public void setCrime_ID(int crime_ID) {
        this.crime_ID = crime_ID;
    }

    public int getTeam_ID() {
        return team_ID;
    }

    public void setTeam_ID(int team_ID) {
        this.team_ID = team_ID;
    }
}
