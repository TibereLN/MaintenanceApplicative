package com.mycalendar.event.eventDescription;

import com.mycalendar.user.User;

public class ProprietaireEvent {
    private final User proprietaire;
    public ProprietaireEvent(User proprietaire) {
        this.proprietaire = proprietaire;
    }
    public User getProprietaire() {
        return proprietaire;
    }
}
