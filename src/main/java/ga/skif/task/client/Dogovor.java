package ga.skif.task.client;

import com.em.validation.client.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

public class Dogovor implements Serializable {

    @NotNull
    @Size(min = 6, max=6, message = "Number must be 6 digits.")
    private Integer nomer;
    @NotNull
    private Date dataZakl;
    @NotNull
    private String strahovatel;
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

    public Dogovor(Integer nomer, Date dataZakl, String strahovatel, Date start, Date end, String premiya) {
        this.nomer = nomer;
        this.dataZakl = dataZakl;
        this.strahovatel = strahovatel;
        this.start = start;
        this.end = end;
        this.premiya = premiya;
    }

    public Dogovor() {
    }

//    public Dogovor(Integer nomer, Date dataZakl, String strahovatel, AddressOb addressOb, Integer strSumma, Date start, Date end, String type, String year, String squair, Date dateRasheta, String premiya) {
//        this.nomer = nomer;
//        this.dataZakl = dataZakl;
//        this.strahovatel = strahovatel;
//        this.addressOb = addressOb;
//        this.strSumma = strSumma;
//        this.start = start;
//        this.end = end;
//        this.type = type;
//        this.year = year;
//        this.squair = squair;
//        this.dateRasheta = dateRasheta;
//        this.premiya = premiya;
//    }

    public Integer getNomer() {
        return nomer;
    }

    public void setNomer(Integer id) {
        this.nomer = nomer;
    }

    public Date getDataZakl() {
        return dataZakl;
    }

    public void setDataZakl(Date dataZakl) {
        this.dataZakl = dataZakl;
    }

    public String getStrahovatel() {
        return strahovatel;
    }

    public void setStrahovatel(String strahovatel) {
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
