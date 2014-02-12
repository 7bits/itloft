package controllers;

import models.Event;
import models.JsonResponse;
import models.Requester;
import models.Subscription;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.joda.time.DateTime;
import play.Logger;
import play.data.validation.Required;
import play.libs.Mail;
import play.mvc.Controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Application extends Controller {

    public static void index() {

        long currentDate = new DateTime(new Date()).getMillis() / 1000l;

        List<Event> pastEvents = Event.find("select o from Event o where date <= ? order by date desc", currentDate).fetch(3);
        Collections.reverse(pastEvents);
        List<Event> futureEvents = Event.find("select o from Event o where date > ? order by date", currentDate).fetch(3);

        render(pastEvents, futureEvents);
    }

    public static void eventImg(long id) {

        final Event event = Event.findById(id);
        if (event != null && event.image != null && event.image.getUUID() != null) {
            response.setContentTypeIfNotSet(event.image.type());
            java.io.InputStream binaryData = event.image.get();
            renderBinary(binaryData);
        }
    }

    public static void subscribe(@Required String emailSub) {

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

    public static void request(@Required String name, @Required String email, @Required String phone, @Required String title, @Required String date) {

        JsonResponse jsonResponse;

        if (name == null || name.isEmpty()) {
            jsonResponse = new JsonResponse("FAIL", "Ошибка. Напишите ваше имя, пожалуйста.", null);
        } else if (email == null || email.isEmpty()) {
            jsonResponse = new JsonResponse("FAIL", "Ошибка. Напишите ваш e-mail, пожалуйста.", null);
        } else if (phone == null || phone.isEmpty()) {
            jsonResponse = new JsonResponse("FAIL", "Ошибка. Напишите ваш телефонный номер, пожалуйста.", null);
        } else {
            jsonResponse = new JsonResponse("SUCCESS", "Заявка отправлена.", null);
        }

        Requester requester;
        if (!"FAIL".equals(jsonResponse.getStatus())) {
            requester = new Requester(name, email, phone, title, date).save();

            SimpleEmail simpleEmail = new SimpleEmail();
            try {
                simpleEmail.setCharset("utf-8");
                simpleEmail.setFrom("at@7bits.it");
                simpleEmail.addTo("at@7bits.it");
                simpleEmail.setSubject("IT-LOFT");
                simpleEmail.setMsg(
                    "Заявка на участие:\n\n  name: " + name
                        + "\n  email: " + email
                        + "\n  phone: " + phone
                        + "\n  title: " + title
                        + "\n  date: " + date
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