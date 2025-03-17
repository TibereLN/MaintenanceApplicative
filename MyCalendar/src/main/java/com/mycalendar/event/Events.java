package com.mycalendar.event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.mycalendar.event.eventType.Event;
import com.mycalendar.event.eventType.EventPeriodique;

public class Events {
    private List<Event> events;

    public Events() {
        events = new ArrayList<Event>();
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        List<Event> events = new ArrayList<>();
        for (Event event : this.getEvents()) {
            if (event instanceof EventPeriodique) {
                LocalDateTime temp = event.dateDebut.getDateDebut();
                while (temp.isBefore(fin)) {
                    if (!temp.isBefore(debut) && !temp.isAfter(fin)) {
                        events.add(event);
                        break;
                    }
                    temp = temp.plusDays(((EventPeriodique) event).frequenceJours.getFrequenceJours());
                }
            } else if (!event.dateDebut.getDateDebut().isBefore(debut) && !event.dateDebut.getDateDebut().isAfter(fin)) {
                events.add(event);
            }
        }
        return events;
    }
}
