package models.views;

import models.Event;
import play.db.jpa.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * View for Events History, one month
 */
public class EventsHistoryMonthView extends Model {

    /** Month name */
    public String month;

    /** List of events for this month */
    public List<Event> events;

    public EventsHistoryMonthView() {
    }

    public EventsHistoryMonthView(final String month) {
        this.month = month;
        this.events = new ArrayList<Event>();
    }
}
