package models;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;
import utils.DateTimeConverter;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Subscription extends Model {

    private static final Long MILLIS_IN_SECOND = 1000L;

    @Email
    @Required
    public String email;

    public Long createdAt;

    public Subscription(final String email) {
        this.email = email;
        this.createdAt = (new LocalDateTime().toDateTime(DateTimeZone.UTC)).getMillis() / MILLIS_IN_SECOND;
    }

    @Transient
    public String getHumanReadableCreatedAtDate() {

        return DateTimeConverter.fromLong(createdAt);
    }
}
