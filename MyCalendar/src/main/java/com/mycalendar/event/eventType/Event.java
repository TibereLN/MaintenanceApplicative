package com.mycalendar.event.eventType;

import com.mycalendar.event.eventDescription.*;
import com.mycalendar.user.User;

import java.time.LocalDateTime;

public abstract class Event {
    public idEvent id;
    public TitreEvent titre;
    public ProprietaireEvent proprietaire;
    public DateDebutEvent dateDebut;
    public DureeMinutesEvent dureeMinutes;

    public Event(int id, String titre, User proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        this.id = new idEvent(id);
        this.titre = new TitreEvent(titre);
        this.proprietaire = new ProprietaireEvent(proprietaire);
        this.dateDebut = new DateDebutEvent(dateDebut);
        this.dureeMinutes = new DureeMinutesEvent(dureeMinutes);
    }

   abstract public String description();
}