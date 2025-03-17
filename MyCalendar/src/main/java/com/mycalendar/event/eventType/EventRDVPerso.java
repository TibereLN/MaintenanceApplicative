package com.mycalendar.event.eventType;

import com.mycalendar.event.eventDescription.*;

import java.time.LocalDateTime;

public class EventRDVPerso extends Event {

    public EventRDVPerso(String titre, String proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        super(titre, proprietaire, dateDebut, dureeMinutes);
    }

    @Override
    public String description() {
        return "RDV : " + titre + " à " + dateDebut.toString();
    }
}
