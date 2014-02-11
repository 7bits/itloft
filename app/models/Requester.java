package models;

import org.joda.time.DateTime;
import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;
import utils.DateTimeConverter;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Date;

@Entity
public class Requester extends Model {

    @Required
    public String name;

    @Email
    @Required
    public String email;

    @Required
    public String phone;

    @Required
    public String title;

    @Required
    public String date;

    public Long createdAt;

    public Requester(final String name, final String email, final String phone, final String title, final String date) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.title = title;
        this.date = date;
        this.createdAt = new DateTime(new Date()).getMillis() / 1000L;
    }

    @Transient
    public String getHumanReadableCreatedAtDate() {

        return DateTimeConverter.fromLong(createdAt);
    }
}
