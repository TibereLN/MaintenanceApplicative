package com.mycalendar.event.eventDescription;

import java.util.List;

public class ParticipantsEvent {
    private final List<String> participants;
    public ParticipantsEvent(List<String> participants) {
        if (!participants.isEmpty()) this.participants = participants;
        else throw new IllegalArgumentException("participants list is empty");
    }
    public List<String> getParticipants() {
        return participants;
    }
}
