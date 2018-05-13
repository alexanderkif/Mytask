package ga.saha.gwt.shared;

import com.google.gwt.i18n.client.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

@Entity
@Table(name = "dogovors")
public class Dogovor implements Serializable {

    @Id
    @Column(name = "iddog", unique = true, nullable = false)
    private Long iddog;
    @Column(name = "datazakl", nullable = false)
    private Date datazakl;
    @Column(name = "strahovatelid", nullable = false)
    private Long strahovatelid;
    @Column(name = "strsumma", nullable = false)
    private Integer strsumma;
    @Column(name = "startdate", nullable = false)
    private Date startdate;
    @Column(name = "enddate", nullable = false)
    private Date enddate;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "year", nullable = false)
    private  String year;
    @Column(name = "squair", nullable = false)
    private  String squair;
    @Column(name = "daterasheta")
    private  Date daterasheta;
    @Column(name = "premiya")
    private  String premiya;
    @Column(name = "state", nullable = false)
    private String state;
    @Column(name = "index")
    private String index;
    @Column(name = "krai", nullable = false)
    private String krai;
    @Column(name = "district")
    private String district;
    @Column(name = "town", nullable = false)
    private String town;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "home")
    private Integer home;
    @Column(name = "korpus")
    private String korpus;
    @Column(name = "stroenie")
    private String stroenie;
    @Column(name = "flat", nullable = false)
    private Integer flat;
    @Column(name = "comment")
    private String comment;
    @Column(name = "fullname")
    private String fullname;

    public Dogovor() {
    }

    public Dogovor(Long iddog, Date datazakl, Long strahovatelid, Integer strsumma, Date startdate, Date enddate, String type, String year, String squair, Date daterasheta, String premiya, String state, String index, String krai, String district, String town, String street, Integer home, String korpus, String stroenie, Integer flat, String comment, String fullname) {
        this.iddog = iddog;
        this.datazakl = datazakl;
        this.strahovatelid = strahovatelid;
        this.strsumma = strsumma;
        this.startdate = startdate;
        this.enddate = enddate;
        this.type = type;
        this.year = year;
        this.squair = squair;
        this.daterasheta = daterasheta;
        this.premiya = premiya;
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
        this.fullname = fullname;
    }

    public List<String> raschet(String strSumText, String typeNedvizhText, String godPostrText, String ploshadText,
                                Date startDate, Date endDate){
        Integer strSum = 0;
        Double koTH = 0.0, koGP = 0.0, koPL = 0.0;
        String errString = "";
        Date today = new Date();

        if (!isInteger(strSumText)) {
            errString += "Страховая сумма должна быть целым числом. ";
        } else {
            strSum = Integer.valueOf(strSumText);
        }

        if (Objects.equals(typeNedvizhText, "")) {
            errString += "Тип недвижимости должен быть выбран. ";
        } else {
            switch (typeNedvizhText) {
                case "Квартира":
                    koTH = 1.7;
                    break;
                case "Дом":
                    koTH = 1.5;
                    break;
                default:
                    koTH = 1.3;
            }
        }

        if (!isInteger(godPostrText) || godPostrText.length() != 4) {
            errString += "Введите год постройки (4 цифры). ";
        } else {
            if (Integer.valueOf(godPostrText) > Integer.valueOf(DateTimeFormat.getFormat("yyyy").format(today))) {
                errString += "Дом еще не построен. ";
            } else {
                if (Integer.valueOf(godPostrText) < 2000) {
                    koGP = 1.3;
                } else {
                    if (Integer.valueOf(godPostrText) < 2014) {
                        koGP = 1.6;
                    } else {
                        koGP = 2.0;
                    }
                }
            }
        }

        if (!isNumeric(ploshadText)) {
            errString += "Введите правильно площадь. ";
        } else {
            if (Double.valueOf(ploshadText) < 50) {
                koPL = 1.2;
            } else {
                if (Double.valueOf(ploshadText) < 100) {
                    koPL = 1.5;
                } else {
                    koPL = 2.0;
                }
            }
        }

        if (startDate.getTime() - today.getTime() < -1000 * 60 * 60 * 24) {
            errString += "Дата начала договора не может быть меньше текущей даты. ";
        }

        if (endDate.getTime() - startDate.getTime() < 0) {
            errString += "Дата окончания договора не может быть меньше даты начала. ";
        }

        if (TimeUnit.DAYS.convert(
                endDate.getTime() - startDate.getTime(),
                TimeUnit.MILLISECONDS) > 365) {
            errString += "Договор не может действовать дольше года. ";
        }

        double prem = 0;
        if (errString.equals("")) {
            //Страховая премия = (Страховая сумма / кол-во дней) * Коэф.ТН * Коэф.ГП * Коэф.Пл
            prem = ((int) (strSum * koTH * koGP * koPL * 100 / TimeUnit.DAYS.convert(
                    endDate.getTime() - startDate.getTime(),
                    TimeUnit.MILLISECONDS))) / 100.0;
        }
        return new ArrayList<>(asList(errString, String.valueOf(prem)));
    }


    private static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static boolean isInteger(String str) {
        try {
            double d = Double.parseDouble(str);
            if (d % 1 == 0) {
                return true;
            }
        } catch (NumberFormatException ignored) {
        }
        return false;
    }

    @Override
    public String toString() {
        return "{" +
                "iddog:" + iddog +
                ", datazakl:" + datazakl +
                ", strahovatelid:" + strahovatelid +
                ", strsumma:" + strsumma +
                ", startdate:" + startdate +
                ", enddate:" + enddate +
                ", type:'" + type + '\'' +
                ", year:'" + year + '\'' +
                ", squair:'" + squair + '\'' +
                ", daterasheta:" + daterasheta +
                ", premiya:'" + premiya + '\'' +
                ", state:'" + state + '\'' +
                ", index:'" + index + '\'' +
                ", krai:'" + krai + '\'' +
                ", district:'" + district + '\'' +
                ", town:'" + town + '\'' +
                ", street:'" + street + '\'' +
                ", home:" + home +
                ", korpus:'" + korpus + '\'' +
                ", stroenie:'" + stroenie + '\'' +
                ", flat:" + flat +
                ", comment:'" + comment + '\'' +
                '}';
    }

    public Long getIddog() {
        return iddog;
    }

    public void setIddog(Long iddog) {
        this.iddog = iddog;
    }

    public Date getDatazakl() {
        return datazakl;
    }

    public void setDatazakl(Date datazakl) {
        this.datazakl = datazakl;
    }

    public Long getStrahovatelid() {
        return strahovatelid;
    }

    public void setStrahovatelid(Long strahovatelid) {
        this.strahovatelid = strahovatelid;
    }

    public Integer getStrsumma() {
        return strsumma;
    }

    public void setStrsumma(Integer strsumma) {
        this.strsumma = strsumma;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
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

    public Date getDaterasheta() {
        return daterasheta;
    }

    public void setDaterasheta(Date daterasheta) {
        this.daterasheta = daterasheta;
    }

    public String getPremiya() {
        return premiya;
    }

    public void setPremiya(String premiya) {
        this.premiya = premiya;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
