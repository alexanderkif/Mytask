package ga.skif.task.client;

import org.bson.Document;

import java.io.Serializable;
import java.util.Date;

public class Strahovatel implements Serializable {

    private String id;
    private String lastName;
    private String firstName;
    private String firstName2;
    private Date birth;
    private Integer passportSeria;
    private Integer passportNumber;

    public Strahovatel(String lastName, String firstName, String firstName2) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.firstName2 = firstName2;
    }

    public Strahovatel() {
    }

    public Integer getPassportSeria() {
        return passportSeria;
    }

    public void setPassportSeria(Integer passportSeria) {
        this.passportSeria = passportSeria;
    }

    public Integer getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(Integer passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getFullName(){
        return this.lastName+" "+this.firstName+" "+this.firstName2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
