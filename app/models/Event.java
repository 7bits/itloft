package models;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;
import services.MailNotification;
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

    @Required
    public Boolean notifySubscribers;

    /** Needed because on setting notifySubscribers model is not filled completely */
    @Transient
    public Boolean sendNotification = false;

    public Event(final Long date, final String title, final String description, final Blob image, final String reference) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.image = image;
        this.reference = reference;
        this.notifySubscribers = false;
    }

    public void setDate(final Long date) {
        this.date = date;
        if (sendNotification != null && sendNotification) {
            MailNotification.notifySubscribersNewEvent(this);
        }
    }

    @Transient
    public String getHumanReadableDate() {

        return DateTimeConverter.fromLong(date);
    }

    @Transient
    public String getDescriptionReadable() {

        return description.replace("\n", "<br/>\n");
    }

    @Transient
    public String getImageUUID() {

        return image.getUUID();
    }

    public void setNotifySubscribers(final Boolean notifySubscribers) {
        if (notifySubscribers && (this.notifySubscribers == null || !this.notifySubscribers)) {
            sendNotification = true;
        }
        this.notifySubscribers = notifySubscribers;
    }

}
