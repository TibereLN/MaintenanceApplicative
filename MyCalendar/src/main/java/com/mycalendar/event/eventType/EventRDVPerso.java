package com.mycalendar.event.eventType;

import com.mycalendar.user.User;

import java.time.LocalDateTime;

public class EventRDVPerso extends Event {

    public EventRDVPerso(int id,String titre, User proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        super(id, titre, proprietaire, dateDebut, dureeMinutes);
    }

    @Override
    public String description() {
        return this.id.getId() + " RDV : " + titre.getTitre() + " à " + dateDebut.getDateDebut().toString();
    }
}
