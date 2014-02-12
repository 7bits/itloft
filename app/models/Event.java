package models;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Blob;
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
    public Blob image;

    @Required
    public String reference;

    public Event(final Long date, final String title, final String description, final Blob image, final String reference) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.image = image;
        this.reference = reference;
    }

    @Transient
    public String getHumanReadableDate() {

        return DateTimeConverter.fromLong(date);
    }

    @Transient
    public String getImageUUID() {

        return image.getUUID();
    }
}
