package com.mycalendar.event.eventType;

import com.mycalendar.user.User;

import java.time.LocalDateTime;

public class EventRDVPerso extends Event {

    public EventRDVPerso(String titre, User proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        super(titre, proprietaire, dateDebut, dureeMinutes);
    }

    @Override
    public String description() {
        return "RDV : " + titre.getTitre() + " à " + dateDebut.getDateDebut().toString();
    }
}
