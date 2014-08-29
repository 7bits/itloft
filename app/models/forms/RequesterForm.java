package models.forms;

import play.data.binding.As;
import play.data.validation.Email;
import play.data.validation.Phone;
import play.data.validation.Required;
import play.data.validation.URL;
import play.db.jpa.Model;

import java.io.File;
import java.util.Date;

/**
 * Form for Request Event
 */
public class RequesterForm extends Model {

    @Required
    public String name;

    @Email
    @Required
    public String email;

    @Phone
    @Required
    public String phone;

    @Required
    public String title;

    @Required
    public Date startDate;

    @Required
    public Date endDate;

    @Required
    public String description;

    @Required
    public File logo;

    @URL
    @Required
    public String link;
}
