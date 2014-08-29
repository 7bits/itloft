package models.forms;

import play.data.binding.As;
import play.data.validation.CheckWith;
import play.data.validation.Email;
import play.data.validation.InFuture;
import play.data.validation.Phone;
import play.data.validation.Required;
import play.data.validation.URL;
import play.db.jpa.Model;
import utils.validators.DateAfterDateCheck;

import java.io.File;
import java.util.Date;

/**
 * Form for Request Event
 */
public class RequesterForm extends Model {

    @Required
    public String name;

    @Required
    @Email
    public String email;

    @Required
    @Phone
    public String phone;

    @Required
    public String title;

    @Required
    @InFuture
    public Date startDate;

    @Required
    @InFuture
    @CheckWith(DateAfterDateCheck.class)
    public Date endDate;

    @Required
    public String description;

    @Required
    public File logo;

    @Required
    @URL
    public String link;
}
