package models;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import play.data.validation.Email;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;
import play.libs.MimeTypes;
import utils.DateTimeConverter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Transient;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

@Entity
public class Requester extends Model {

    private static final Long MILLIS_IN_SECOND = 1000L;

    @Required
    public String name;

    @Required
    @Email
    public String email;

    @Required
    public String phone;

    @Required
    public String title;

    @Required
    public Long startDate;

    @Required
    public Long endDate;

    @Lob
    @Required
    @MaxSize(1000)
    public String description;

    @Required
    public Blob logo;

    @Required
    public String link;

    public Long createdAt;

    public Requester(
            final String name,
            final String email,
            final String phone,
            final String title,
            final Date startDate,
            final Date endDate,
            final String description,
            final File logo,
            final String link
    ) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.title = title;
        // Because Play converts time received in form to server timezone, we need timezone conversion
        this.startDate = (new LocalDateTime(startDate).toDateTime(DateTimeZone.UTC)).getMillis() / MILLIS_IN_SECOND;
        this.endDate = (new LocalDateTime(endDate).toDateTime(DateTimeZone.UTC)).getMillis() / MILLIS_IN_SECOND;
        this.description = description;
        Blob tempLogo = new Blob();
        try {
            tempLogo.set(new FileInputStream(logo), MimeTypes.getContentType(logo.getName()));
        } catch (FileNotFoundException e) {
            tempLogo = null;
        }
        this.logo = tempLogo;
        this.link = link;
        this.createdAt = (new LocalDateTime().toDateTime(DateTimeZone.UTC)).getMillis() / MILLIS_IN_SECOND;
    }

    @Transient
    public String getHumanReadableCreatedAtDate() {

        return DateTimeConverter.fromLongWithTime(createdAt);
    }


    @Transient
    public String getHumanReadableStartDate() {

        return DateTimeConverter.fromLongWithTime(startDate);
    }


    @Transient
    public String getHumanReadableEndDate() {

        return DateTimeConverter.fromLongWithTime(endDate);
    }
}
