package ga.skif.task.client.entity;

import com.em.validation.client.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AddressOb implements Serializable {
    @NotEmpty
    private String state;
    private String index;
    @NotEmpty
    private String krai;
    private String district;
    @NotEmpty
    private String town;
    @NotEmpty
    private String street;
    private Integer home;
    private String korpus;
    private String stroenie;
    @NotNull
    private Integer flat;
    private String comment;

    public AddressOb() {
    }

    public AddressOb(String state, String index, String krai, String district, String town, String street, Integer home, String korpus, String stroenie, Integer flat, String comment) {
        this.state = state;
        this.index = index;
        this.krai = krai;
        this.district = district;
        this.town = town;
        this.street = street;
        this.home = home;
        this.korpus = korpus;
        this.stroenie = stroenie;
        this.flat = flat;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "AddressOb{" +
                "state='" + state + '\'' +
                ", index='" + index + '\'' +
                ", krai='" + krai + '\'' +
                ", district='" + district + '\'' +
                ", town='" + town + '\'' +
                ", street='" + street + '\'' +
                ", home=" + home +
                ", korpus='" + korpus + '\'' +
                ", stroenie='" + stroenie + '\'' +
                ", flat=" + flat +
                ", comment='" + comment + '\'' +
                '}';
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getKrai() {
        return krai;
    }

    public void setKrai(String krai) {
        this.krai = krai;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHome() {
        return home;
    }

    public void setHome(Integer home) {
        this.home = home;
    }

    public String getKorpus() {
        return korpus;
    }

    public void setKorpus(String korpus) {
        this.korpus = korpus;
    }

    public String getStroenie() {
        return stroenie;
    }

    public void setStroenie(String stroenie) {
        this.stroenie = stroenie;
    }

    public Integer getFlat() {
        return flat;
    }

    public void setFlat(Integer flat) {
        this.flat = flat;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
