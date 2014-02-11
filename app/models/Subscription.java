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
public class Subscription extends Model {

    @Email
    @Required
    public String email;

    public Long createdAt;

    public Subscription(final String email) {
        this.email = email;
        this.createdAt = new DateTime(new Date()).getMillis() / 1000L;
    }

    @Transient
    public String getHumanReadableCreatedAtDate() {

        return DateTimeConverter.fromLong(createdAt);
    }
}
