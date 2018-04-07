package ga.skif.task.client.entity;

import com.em.validation.client.constraints.NotEmpty;
import com.google.gwt.i18n.client.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

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
    @NotNull(groups = Minimal.class)
    private Integer strSumma;
    @NotNull(groups = Minimal.class)
    private Date start;
    @NotNull(groups = Minimal.class)
    private Date end;
    @NotEmpty(groups = Minimal.class, message = "Тип недвижимости должен быть выбран. Валидатор")
    private String type;
    @NotEmpty
    @Size(groups = Minimal.class, min = 4, max=4, message = "Year must be 4 digits.")
    private  String year;
    @NotEmpty(groups = Minimal.class, message = "Введите правильно площадь. Валидатор")
    private  String squair;
    private  Date dateRasheta;
    private  String premiya;

    public interface Minimal { }

    Date today = new Date();

    public Dogovor() {
    }

    public List<String> raschet(String strSumText, String typeNedvizhText, String godPostrText, String ploshadText,
                          Date startDate, Date endDate){
        Integer strSum = 0;
        Double koTH = 0.0, koGP = 0.0, koPL = 0.0;
        String errString = "";

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
