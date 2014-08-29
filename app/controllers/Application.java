package controllers;

import models.Event;
import models.JsonResponse;
import models.Requester;
import models.Subscription;
import models.forms.RequesterForm;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.joda.time.DateTime;
import play.Logger;
import play.data.validation.*;
import play.data.validation.Error;
import play.libs.Mail;
import play.mvc.Controller;
import utils.ValidationUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Application extends Controller {

    public static void index() {

        long currentDate = new DateTime(new Date()).getMillis() / 1000l;

        List<Event> pastEvents = Event.find("select o from Event o where date <= ? order by date desc", currentDate).fetch(3);
        Collections.reverse(pastEvents);
        List<Event> futureEvents = Event.find("select o from Event o where date > ? order by date", currentDate).fetch(3);

        render(pastEvents, futureEvents);
    }

    public static void eventImg(final long id) {

        final Event event = Event.findById(id);
        if (event != null && event.image != null && event.image.getUUID() != null) {
            response.setContentTypeIfNotSet(event.image.type());
            java.io.InputStream binaryData = event.image.get();
            renderBinary(binaryData);
        }
    }

    public static void subscribe(@Required final String emailSub) {

        JsonResponse jsonResponse;

        if (emailSub != null && !emailSub.isEmpty()) {
            jsonResponse = new JsonResponse("SUCCESS", "Подписка прошла успешно.", null);
        } else {
            jsonResponse = new JsonResponse("FAIL", "Ошибка. Напишите ваш e-mail, пожалуйста.", null);
        }

        Subscription subscription;
        if (!"FAIL".equals(jsonResponse.getStatus())) {
            subscription = new Subscription(emailSub).save();

            SimpleEmail email = new SimpleEmail();
            try {
                email.setCharset("utf-8");
                email.setFrom("at@7bits.it");
                email.addTo("at@7bits.it");
                email.setSubject("IT-LOFT");
                email.setMsg("Подписка на события:\n\n  email: " + emailSub + "\n  createdAt: " + subscription.getHumanReadableCreatedAtDate());
            } catch (EmailException e) {
                Logger.error("Could not send email", e);
            }
            Mail.send(email);
        }

        renderJSON(jsonResponse);
    }

    public static void request(@Valid final RequesterForm requesterForm) {

        JsonResponse jsonResponse;

        if(validation.hasErrors()) {
            final Map<String, List<Error>> errors = validation.errorsMap();
            String formName = "requesterForm";
            jsonResponse = new JsonResponse(
                    "FAIL",
                    ValidationUtils.extractMainValidationError(errors, formName),
                    ValidationUtils.extractFieldValidationErrors(errors, formName));
        } else {
            jsonResponse = new JsonResponse("SUCCESS", "Заявка отправлена.", null);
        }

        Requester requester;
        if (!"FAIL".equals(jsonResponse.getStatus())) {
            requester = new Requester(
                    requesterForm.name,
                    requesterForm.email,
                    requesterForm.phone,
                    requesterForm.title,
                    requesterForm.date
            ).save();

            SimpleEmail simpleEmail = new SimpleEmail();
            try {
                simpleEmail.setCharset("utf-8");
                simpleEmail.setFrom("at@7bits.it");
                simpleEmail.addTo("at@7bits.it");
                simpleEmail.setSubject("IT-LOFT");
                simpleEmail.setMsg(
                    "Заявка на участие:\n\n  name: " + requester.name
                        + "\n  email: " + requester.email
                        + "\n  phone: " + requester.phone
                        + "\n  title: " + requester.title
                        + "\n  date: " + requester.date
                        + "\n  createdAt: " + requester.getHumanReadableCreatedAtDate()
                );
            } catch (EmailException e) {
                Logger.error("Could not send email", e);
            }
            Mail.send(simpleEmail);
        }

        renderJSON(jsonResponse);
    }
}