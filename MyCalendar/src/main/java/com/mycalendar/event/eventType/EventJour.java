package com.mycalendar.event.eventType;

import com.mycalendar.user.User;

import java.time.LocalDateTime;

public class EventJour extends Event {
    public EventJour(int id, String titre, User proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        super(id, titre, proprietaire, dateDebut, dureeMinutes);
    }

    @Override
    public String description() {
        return this.id.getId() + " Jour important '" + titre.getTitre() + "' le " + dateDebut.getDateDebut().toString();
    }
}
