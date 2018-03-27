package ga.skif.task.client;

import com.em.validation.client.constraints.NotEmpty;
import org.bson.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class Strahovatel implements Serializable {
    @NotNull
    private String id;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String firstName;
    private String firstName2;
    @NotNull
    private Date birth;
    @NotNull
    @Size(min = 4, max=4, message = "Seriya must be 4 digits.")
    private Integer passportSeria;
    @NotNull
    @Size(min = 6, max=6, message = "Number must be 6 digits.")
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

    @Override
    public String toString() {
        return "Strahovatel{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", firstName2='" + firstName2 + '\'' +
                ", birth=" + birth +
                ", passportSeria=" + passportSeria +
                ", passportNumber=" + passportNumber +
                '}';
    }
}
