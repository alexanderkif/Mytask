package ga.skif.task.client;

import java.util.*;

public class Dogovor {

    private Integer id;
    private Date dataZakl;
    private Strahovatel strahovatel;
    private AddressOb addressOb;
    private Integer strSumma;
    private Date start;
    private Date end;
    private List type;
    private  String year;
    private  String squair;
    private  Date dateRasheta;
    private  String premiya;

    public Dogovor(Integer id, Date dataZakl, Strahovatel strahovatel, Date start, Date end, String premiya) {
        this.id = id;
        this.dataZakl = dataZakl;
        this.strahovatel = strahovatel;
        this.start = start;
        this.end = end;
        this.premiya = premiya;
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

    public List getType() {
        return type;
    }

    public void setType(List type) {
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
