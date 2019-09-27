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
    private ArrayList<Security> securities;

    private LocalDate improved_egrul_date;

    public boolean check() {
        boolean securitie_flag = true;
        for (Security security : securities) {
            securitie_flag &= security.check();
        }
        return (egrul_date != null && !egrul_date.isEmpty() &&
                name_full != null && !name_full.isEmpty() &&
                name_short != null && !name_short.isEmpty() &&
                securitie_flag);
    }

    public void improve(boolean flag) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        if (flag) {
            for (Security security : securities) {
                security.improve(0);
            }
        }
        this.improved_egrul_date = LocalDate.parse(egrul_date, formatter);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameFull() {
        return name_full;
    }

    public void setNameFull(String name_full) {
        this.name_full = name_full;
    }

    public String getNameShort() {
        return name_short;
    }

    public void setNameShort(String name_short) {
        this.name_short = name_short;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public CompanyType getCompanyType() {
        return company_type;
    }

    public void setCompanyType(CompanyType company_type) {
        this.company_type = company_type;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public LocalDate getEgrulDate() {
        return improved_egrul_date;
    }

    public void setEgrulDate(LocalDate egrul_date) {
        this.improved_egrul_date = egrul_date;
    }

    public void setEgrulDate(String egrul_date) {
        this.egrul_date = egrul_date;
        if (egrul_date != null && !egrul_date.isEmpty()) {
            improve(false);
        } else {
            System.err.println("Error: wrong new date");
            throw new Error();
        }
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getFioHead() {
        return fio_head;
    }

    public void setFioHead(String fio_head) {
        this.fio_head = fio_head;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEMail() {
        return e_mail;
    }

    public void setEMail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }

    public boolean isIsResident() {
        return is_resident;
    }

    public void setIsResident(boolean is_resident) {
        this.is_resident = is_resident;
    }

    public ArrayList<Security> getSecurities() {
        return securities;
    }

    public void setSecurities(ArrayList<Security> securities) {
        this.securities = securities;
    }


}