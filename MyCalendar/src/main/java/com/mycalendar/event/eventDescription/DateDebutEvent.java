package com.mycalendar.event.eventDescription;

import java.time.LocalDateTime;

public class DateDebutEvent {
    private final LocalDateTime dateDebut;
    public DateDebutEvent(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }
    public LocalDateTime getDateDebut() {
        return dateDebut;
    }
}
