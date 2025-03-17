package com.mycalendar.event.eventDescription;

public class DureeMinutesEvent {
    private final int dureeMinutes;
    public DureeMinutesEvent(int dureeMinutes) {
        if (dureeMinutes < 0) this.dureeMinutes = dureeMinutes;
        else throw new IllegalArgumentException("DureeMinutes must be greater than 0");
    }
    public int getDureeMinutes() {
        return dureeMinutes;
    }
}
