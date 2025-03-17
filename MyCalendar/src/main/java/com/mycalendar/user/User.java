package com.mycalendar.user;

public class User {
    private String name;
    private String mdp;

    public User(String name, String mdp) {
        this.name = name;
        this.mdp = mdp;
    }
    public String getName() {
        return name;
    }
    public String getMdp() {
        return mdp;
    }
}
