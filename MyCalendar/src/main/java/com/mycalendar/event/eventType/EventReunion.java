package com.mycalendar.event.eventType;

import com.mycalendar.event.eventDescription.*;
import com.mycalendar.user.User;

import java.time.LocalDateTime;
import java.util.List;

public class EventReunion extends Event {
    public LieuEvent lieu;
    public ParticipantsEvent participants;

    public EventReunion(String titre, User proprietaire, LocalDateTime dateDebut, int dureeMinutes, String lieu, List<String> participants) {
        super(titre, proprietaire, dateDebut, dureeMinutes);
        this.lieu = new LieuEvent(lieu);
        this.participants = new ParticipantsEvent(participants);
    }

    public String description() {
        return "Réunion : " + titre.getTitre() + " à " + lieu.getLieu() + " avec " + participants.getParticipants();
    }
}
