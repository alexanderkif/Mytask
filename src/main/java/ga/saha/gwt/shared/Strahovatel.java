package ga.saha.gwt.shared;

import com.google.gwt.core.client.JavaScriptObject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "strahovatels")
public class Strahovatel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idstrah", unique = true, nullable = false)
    private Long idstrah;
    private String lastname;
    private String firstname;
    private String firstname2;
    private Date birth;
    private Integer passportseria;
    private Integer passportnumber;

    public Strahovatel(String lastname, String firstname, String firstname2) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.firstname2 = firstname2;
    }

    public Strahovatel() {
    }

    public Strahovatel(Long idstrah, String lastname, String firstname, String firstname2, Date birth, Integer passportseria, Integer passportnumber) {
        this.idstrah = idstrah;
        this.lastname = lastname;
        this.firstname = firstname;
        this.firstname2 = firstname2;
        this.birth = birth;
        this.passportseria = passportseria;
        this.passportnumber = passportnumber;
    }

    public String getFullName(){
        return this.lastname +" "+this.firstname +" "+this.firstname2;
    }

    public Long getIdstrah() {
        return idstrah;
    }

    public void setIdstrah(Long idstrah) {
        this.idstrah = idstrah;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname2() {
        return firstname2;
    }

    public void setFirstname2(String firstname2) {
        this.firstname2 = firstname2;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getPassportseria() {
        return passportseria;
    }

    public void setPassportseria(Integer passportseria) {
        this.passportseria = passportseria;
    }

    public Integer getPassportnumber() {
        return passportnumber;
    }

    public void setPassportnumber(Integer passportnumber) {
        this.passportnumber = passportnumber;
    }

    @Override
    public String toString() {
        return "{\"idstrah\":" + idstrah + ", \"lastname\":\"" + lastname + "\"" + ", \"firstname\":\""
                + firstname + "\"" + ", \"firstname2\":\"" + firstname2 + "\"" + ", \"birth\":\"" + birth + "\""
                + ", \"passportseria\":" + passportseria + ", \"passportnumber\":" + passportnumber + "}";
    }
}
