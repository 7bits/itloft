package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Requester extends Model {

    public String name;
    public String email;
    public String phone;
    public Date createdAt;

    public Requester(final String name, final String email, final String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.createdAt = new Date();
    }
}
