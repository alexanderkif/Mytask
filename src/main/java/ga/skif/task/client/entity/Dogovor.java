package ga.skif.task.client.entity;

import com.em.validation.client.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

public class Dogovor implements Serializable {

    @NotNull
    @Size(min = 6, max=6, message = "Number must be 6 digits.")
    private Integer id;
    @NotNull
    private Date dataZakl;
    @NotNull
    private Strahovatel strahovatel;
    @NotNull
    private AddressOb addressOb;
    @NotNull
    private Integer strSumma;
    @NotNull
    private Date start;
    @NotNull
    private Date end;
    @NotEmpty
    private String type;
    @NotEmpty
    private  String year;
    @NotEmpty
    private  String squair;
    private  Date dateRasheta;
    private  String premiya;

    public Dogovor() {
    }

    @Override
    public String toString() {
        return "Dogovor{" +
                "id=" + id +
                ", dataZakl=" + dataZakl +
                ", strahovatel=" + strahovatel +
                ", addressOb=" + addressOb +
                ", strSumma=" + strSumma +
                ", start=" + start +
                ", end=" + end +
                ", type='" + type + '\'' +
                ", year='" + year + '\'' +
                ", squair='" + squair + '\'' +
                ", dateRasheta=" + dateRasheta +
                ", premiya='" + premiya + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataZakl() {
        return dataZakl;
    }

    public void setDataZakl(Date dataZakl) {
        this.dataZakl = dataZakl;
    }

    public Strahovatel getStrahovatel() {
        return strahovatel;
    }

    public void setStrahovatel(Strahovatel strahovatel) {
        this.strahovatel = strahovatel;
    }

    public AddressOb getAddressOb() {
        return addressOb;
    }

    public void setAddressOb(AddressOb addressOb) {
        this.addressOb = addressOb;
    }

    public Integer getStrSumma() {
        return strSumma;
    }

    public void setStrSumma(Integer strSumma) {
        this.strSumma = strSumma;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSquair() {
        return squair;
    }

    public void setSquair(String squair) {
        this.squair = squair;
    }

    public Date getDateRasheta() {
        return dateRasheta;
    }

    public void setDateRasheta(Date dateRasheta) {
        this.dateRasheta = dateRasheta;
    }

    public String getPremiya() {
        return premiya;
    }

    public void setPremiya(String premiya) {
        this.premiya = premiya;
    }
}
