package models;

import org.joda.time.DateTime;
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
        this.startDate = new DateTime(startDate).getMillis() / MILLIS_IN_SECOND;
        this.endDate = new DateTime(endDate).getMillis() / MILLIS_IN_SECOND;
        this.description = description;
        Blob tempLogo = new Blob();
        try {
            tempLogo.set(new FileInputStream(logo), MimeTypes.getContentType(logo.getName()));
        } catch (FileNotFoundException e) {
            tempLogo = null;
        }
        this.logo = tempLogo;
        this.link = link;
        this.createdAt = new DateTime(new Date()).getMillis() / MILLIS_IN_SECOND;
    }

    @Transient
    public String getHumanReadableCreatedAtDate() {

        return DateTimeConverter.fromLong(createdAt);
    }
}
