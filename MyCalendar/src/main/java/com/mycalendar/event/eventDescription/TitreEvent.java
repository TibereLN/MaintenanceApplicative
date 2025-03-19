package com.mycalendar.event.eventDescription;

public class TitreEvent {
    private final String titre;
    public TitreEvent(String titre) {
        if (titre != null) this.titre = titre;
        else throw new NullPointerException("titre is null");
    }
    public String getTitre() {
        return titre;
    }
}
