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

    public Events getEvents() {
        return events;
    }

    public boolean conflit(Event event1, Event event2) {
        LocalDateTime fin1 = event1.dateDebut.getDateDebut().plusMinutes(event1.dureeMinutes.getDureeMinutes());
        LocalDateTime fin2 = event2.dateDebut.getDateDebut().plusMinutes(event2.dureeMinutes.getDureeMinutes());

        if (event1 instanceof EventPeriodique || event2 instanceof EventPeriodique) {
            return false; // Simplification abusive
        }

        if (event1.dateDebut.getDateDebut().isBefore(fin2) && fin1.isAfter(event2.dateDebut.getDateDebut())) {
            return true;
        }
        return false;
    }
}