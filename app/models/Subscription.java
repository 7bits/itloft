package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Subscription extends Model {

    public String email;
    public Date createdAt;

    public Subscription(final String email) {
        this.email = email;
        this.createdAt = new Date();
    }
}
