package models;

import org.joda.time.DateTime;
import play.db.jpa.Model;
import utils.DateTimeConverter;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Date;

@Entity
public class Requester extends Model {

    public String name;
    public String email;
    public String phone;
    public String title;
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
