import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Security {
    private int id;
    private String code;
    private String name_full;
    private String cfi;
    private String date_to;
    private String state_reg_date;
    private State state;
    private Currency currency;

    private LocalDate improved_date_to;
    private LocalDate improved_state_reg_date;

    public boolean check() {
        return (date_to != null && !date_to.isEmpty() &&
                state_reg_date != null && !state_reg_date.isEmpty() &&
                code != null && !code.isEmpty() &&
                currency.getCode() != null && !currency.getCode().isEmpty() &&
                id != 0);
    }

    public void improve(int flag) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        if (flag == 0 || flag == 1) {
            this.improved_date_to = LocalDate.parse(date_to, formatter);
        }
        if (flag == 0 || flag == 2) {
            this.improved_state_reg_date = LocalDate.parse(state_reg_date, formatter);
        }
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

    public String getCfi() {
        return cfi;
    }

    public void setCfi(String cfi) {
        this.cfi = cfi;
    }

    public LocalDate getDateTo() {
        return improved_date_to;
    }

    public void setDateTo(LocalDate date_to) {
        this.improved_date_to = date_to;
    }

    public void setDateTo(String date_to) {
        this.date_to = date_to;
        if (date_to != null && !date_to.isEmpty()) {
            improve(1);
        } else {
            System.err.println("Error: wrong new date");
            throw new Error();
        }
    }

    public LocalDate getStateRegDate() {
        return improved_state_reg_date;
    }

    public void setStateRegDate(LocalDate state_reg_date) {
        this.improved_state_reg_date = state_reg_date;
    }

    public void setStateRegDate(String state_reg_date) {
        this.state_reg_date = state_reg_date;
        if (state_reg_date != null && !state_reg_date.isEmpty()) {
            improve(2);
        } else {
            System.err.println("Error: wrong new date");
            throw new Error();
        }
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getCurrencyCode() {
        return currency.getCode();
    }
}
