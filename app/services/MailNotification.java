package services;

import models.Event;
import models.Requester;
import models.Subscription;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import play.Logger;
import play.db.jpa.JPABase;
import play.libs.Mail;
import utils.DateTimeConverter;

import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Mail Notifications
 */
public class MailNotification {

    /**
     * Notify site administrator when new Requester added
     * @param requester    Requester
     */
    public static void notifyAdminNewRequester(final Requester requester) {
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
                            + "\n  start date: " + requester.startDate
                            + "\n  createdAt: " + requester.getHumanReadableCreatedAtDate()
            );
        } catch (EmailException e) {
            Logger.error("Could not send email", e);
        }
        Mail.send(simpleEmail);
    }

    public static void notifySubscribersNewEvent(final Event event) {
        final List<Subscription> subscriptions = Subscription.findAll();
        if (subscriptions != null && subscriptions.size() > 0) {

            SimpleEmail email = new SimpleEmail();
            try {
                email.setCharset("utf-8");
                email.setFrom("at@7bits.it", "IT Loft");
                email.setSubject("Новое мероприятие в ИТ-лофте");
                email.setMsg(
                        "Добрый день!\n" +
                                "\n" +
                                "Вы подписались на нашу рассылку и мы рады вам сообщить," +
                                " что на сайт лофта http://itlft.ru добавилось новое мероприятие:\n" +
                                event.title + "\n" +
                                event.description + "\n\n" +
                                "Мероприятие будет проходить " + DateTimeConverter.fromLongWithTime(event.date) + "\n" +
                                "Приходите, будет интересно! " +
                                "Вся информация о регистрации - по ссылкам на сайте http://itlft.ru.\n" +
                                "\n" +
                                "--\n" +
                                "С уважением, организаторы IT-лофта."
                );
            } catch (EmailException e) {
                Logger.error("Could not create email", e);
            }

            for (Subscription subscription: subscriptions) {
                try {
                    Set<InternetAddress> to = new HashSet<InternetAddress>();
                    InternetAddress internetAddress = new InternetAddress(subscription.email);
                    to.add(internetAddress);
                    email.setTo(to);
                    Mail.send(email);
                } catch (Exception e) {
                    Logger.error("Could not create email", e);
                }
            }
        }
    }
}
