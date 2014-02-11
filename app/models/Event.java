package models;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;
import utils.DateTimeConverter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Transient;

@Entity
public class Event extends Model {

    @Required
    public Long date;

    @Required
    public String title;

    @Lob
    @Required
    @MaxSize(1000)
    public String description;

    @Required
    public String imageSrc;

    @Required
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
