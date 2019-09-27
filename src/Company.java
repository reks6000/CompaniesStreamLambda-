import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.*;

public class Company {
    private int id;
    private String code;
    private String name_full;
    private String name_short;
    private String inn;
    private CompanyType company_type;
    private String ogrn;
    private String egrul_date;
    private Country country;
    private String fio_head;
    private String address;
    private String phone;
    private String e_mail;
    private String www;
    private boolean is_resident;
    private ArrayList<Securitie> securities;

    private LocalDate improved_egrul_date;

    public boolean check() {
        boolean securitie_flag = true;
        for (Securitie securitie : securities) {
            securitie_flag &= securitie.check();
        }
        return (egrul_date != null && !egrul_date.isEmpty() &&
                name_full != null && !name_full.isEmpty() &&
                name_short != null && !name_short.isEmpty() &&
                securitie_flag);
    }

    public void improve() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        this.improved_egrul_date = LocalDate.parse(egrul_date, formatter);
        for (Securitie securitie : securities) {
            securitie.improve();
        }
    }

    public String getNameFull() {
        return name_full;
    }

    public String getNameShort() {
        return name_short;
    }

    public LocalDate getEgrulDate() {
        return improved_egrul_date;
    }

    public ArrayList<Securitie> getSecurities() {
        return securities;
    }
}