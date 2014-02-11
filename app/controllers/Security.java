package controllers;

import play.Play;

public class Security extends Secure.Security {

    static boolean authenticate(String username, String password) {

        return Play.configuration.get("admin-login").equals(username)
            && Play.configuration.get("admin-password").equals(password);
    }
}
