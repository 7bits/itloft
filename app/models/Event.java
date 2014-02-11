package models;

import play.db.jpa.Model;
import utils.DateTimeConverter;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Event extends Model {

    public Long date;
    public String title;
    public String description;
    public String imageSrc;
    public String reference;

    public Event(final Long date, final String title, final String description, final String imageSrc, final String reference) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.imageSrc = imageSrc;
        this.reference = reference;
    }

    @Transient
    public String getHumanReadableDate() {

        return DateTimeConverter.fromLong(date);
    }
}
