package com.mycalendar;

import com.mycalendar.event.Events;
import com.mycalendar.event.eventType.Event;
import com.mycalendar.event.eventType.EventPeriodique;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CalendarManager {
    public Events events;

    public CalendarManager() {
        this.events = new Events();
    }

    public void ajouterEvent(Event event) {
        events.addEvent(event);
    }

    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        List<Event> result = new ArrayList<>();
        for (Event e : events.getEvents()) {
            if (e instanceof EventPeriodique) {
                LocalDateTime temp = e.dateDebut;
                while (temp.isBefore(fin)) {
                    if (!temp.isBefore(debut)) {
                        result.add(e);
                        break;
                    }
                    temp = temp.plusDays(((EventPeriodique) e).frequenceJours.getFrequenceJours());
                }
            } else if (!e.dateDebut.isBefore(debut) && !e.dateDebut.isAfter(fin)) {
                result.add(e);
            }
        }
        return result;
    }

    public boolean conflit(Event e1, Event e2) {
        LocalDateTime fin1 = e1.dateDebut.plusMinutes(e1.dureeMinutes.getDureeMinutes());
        LocalDateTime fin2 = e2.dateDebut.plusMinutes(e2.dureeMinutes.getDureeMinutes());

        if (e1 instanceof EventPeriodique || e2 instanceof EventPeriodique) {
            return false; // Simplification abusive
        }

        if (e1.dateDebut.isBefore(fin2) && fin1.isAfter(e2.dateDebut)) {
            return true;
        }
        return false;
    }

    public void afficherEvenements() {
        for (Event e : events.getEvents()) {
            System.out.println(e.description());
        }
    }
}