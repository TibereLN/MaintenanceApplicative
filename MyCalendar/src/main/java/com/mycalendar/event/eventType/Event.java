package com.mycalendar.event.eventType;

import com.mycalendar.event.eventDescription.*;

import java.time.LocalDateTime;

public abstract class Event {
    public TitreEvent titre;
    public ProprietaireEvent proprietaire;
    public LocalDateTime dateDebut;
    public DureeMinutesEvent dureeMinutes;

    public Event(String titre, String proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        this.titre = new TitreEvent(titre);
        this.proprietaire = new ProprietaireEvent(proprietaire);
        this.dateDebut = dateDebut;
        this.dureeMinutes = new DureeMinutesEvent(dureeMinutes);
    }

   abstract public String description();
}