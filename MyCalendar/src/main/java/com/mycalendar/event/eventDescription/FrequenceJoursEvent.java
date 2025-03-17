package com.mycalendar.event.eventDescription;

public class FrequenceJoursEvent {
    private final int frequenceJours;
    public FrequenceJoursEvent(int frequenceJours) {
        if (frequenceJours > 0) this.frequenceJours = frequenceJours;
        else throw new IllegalArgumentException("frequenceJours must be greater than 0");
    }
    public int getFrequenceJours() {
        return frequenceJours;
    }
}
