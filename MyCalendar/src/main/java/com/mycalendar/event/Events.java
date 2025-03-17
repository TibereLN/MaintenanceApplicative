package com.mycalendar.event;

import java.util.ArrayList;
import java.util.List;
import com.mycalendar.event.eventType.Event;

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
}
