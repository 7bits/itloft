package models.forms;

import play.data.validation.Email;
import play.data.validation.InFuture;
import play.data.validation.Phone;
import play.data.validation.Required;
import play.data.validation.URL;
import play.db.jpa.Model;
import utils.validators.DateAfter;
import utils.validators.FileType;
import utils.validators.ImageSize;
import utils.validators.ImageTransparency;

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
    @DateAfter(message = "validation.before", before = "startDate")
    public Date endDate;

    @Required
    public String description;

    @Required
    @FileType(message = "validation.not-png-or-gif", types = { "image/jpeg", "image/png" })
    @ImageSize(message = "validation.image-size-100-100", width = 100, height = 100)
    @ImageTransparency
    public File logo;

    @Required
    @URL
    public String link;
}
