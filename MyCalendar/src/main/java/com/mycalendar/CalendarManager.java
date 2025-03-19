package com.mycalendar;

import com.mycalendar.event.Events;
import com.mycalendar.event.eventType.Event;

public class CalendarManager {
    public Events events;

    public CalendarManager() {
        this.events = new Events();
    }

    public void ajouterEvent(Event newEvent) {
        if (!events.eventsDansPeriode(newEvent.dateDebut.getDateDebut(), newEvent.dateDebut.getDateDebut().plusMinutes(newEvent.dureeMinutes.getDureeMinutes())).isEmpty()) {
            System.out.println("Conflit avec un autre évènement");
        }
        else {
            events.addEvent(newEvent);
            System.out.println("Événement ajouté.");
        }
    }

    public void supprimerEvenement(int id) {
       if (events.getEvents().removeIf(event -> event.id.getId() == id)) System.out.println("Evènement supprimé !");
       else System.out.println("Evènement ID inexistant");
    }

    public Events getEvents() {
        return events;
    }
}