package controllers;

import models.JsonResponse;
import models.Requester;
import models.Subscription;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import play.Logger;
import play.data.validation.Required;
import play.libs.Mail;
import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
        render();
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