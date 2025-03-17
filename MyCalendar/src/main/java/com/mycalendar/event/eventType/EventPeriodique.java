package com.mycalendar.event.eventType;

import com.mycalendar.event.eventDescription.*;
import com.mycalendar.user.User;

import java.time.LocalDateTime;

public class EventPeriodique extends Event {
    public FrequenceJoursEvent frequenceJours; // uniquement pour PERIODIQUE

    public EventPeriodique(String titre, User proprietaire, LocalDateTime dateDebut, int dureeMinutes, int frequenceJours) {
        super(titre, proprietaire, dateDebut, dureeMinutes);
        this.frequenceJours = new FrequenceJoursEvent(frequenceJours);
    }

    @Override
    public String description() {
        return "Événement périodique : " + titre.getTitre() + " tous les " + frequenceJours.getFrequenceJours() + " jours";
    }
}
