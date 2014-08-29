package models.forms;

import models.Requester;
import org.joda.time.DateTime;
import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;
import utils.DateTimeConverter;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Date;

public class RequesterForm {

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
}
