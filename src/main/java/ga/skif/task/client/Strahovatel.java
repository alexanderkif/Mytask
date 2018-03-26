package ga.skif.task.client;

import java.io.Serializable;
import java.util.Date;

public class Strahovatel implements Serializable {

    private Integer id;
    private String lastName;
    private String firstName;
    private String firstName2;
    private Date birth;
    private Integer passport;

    public Strahovatel(String lastName, String firstName, String firstName2) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.firstName2 = firstName2;
    }

    public Strahovatel() {
    }

    public String getFullName(){
        return this.lastName+" "+this.firstName+" "+this.firstName2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName2() {
        return firstName2;
    }

    public void setFirstName2(String firstName2) {
        this.firstName2 = firstName2;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getPassport() {
        return passport;
    }

    public void setPassport(Integer passport) {
        this.passport = passport;
    }
}
