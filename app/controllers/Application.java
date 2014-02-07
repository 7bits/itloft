package controllers;

import models.JsonResponse;
import models.Requester;
import models.Subscription;
import play.data.validation.Required;
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

        if (!"FAIL".equals(jsonResponse.getStatus())) {
            new Subscription(emailSub).save();
        }

        renderJSON(jsonResponse);
    }

    public static void request(@Required String name, @Required String email, @Required String phone) {

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

        if (!"FAIL".equals(jsonResponse.getStatus())) {
            new Requester(name, email, phone).save();
        }

        renderJSON(jsonResponse);
    }
}