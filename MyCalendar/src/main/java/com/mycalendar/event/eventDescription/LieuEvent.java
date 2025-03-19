package com.mycalendar.event.eventDescription;

public class LieuEvent {
    private final String lieu;
    public LieuEvent(String lieu) {
        if (lieu != null) this.lieu = lieu;
        else throw new NullPointerException("lieu is null");
    }
    public String getLieu() {
        return lieu;
    }
}
