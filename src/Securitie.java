import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Securitie {
    private String company_name;
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

    public void improve() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        this.improved_date_to = LocalDate.parse(date_to, formatter);
        this.improved_state_reg_date = LocalDate.parse(state_reg_date, formatter);
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public LocalDate getDateTo() {
        return improved_date_to;
    }

    public String getCurrencyCode() {
        return currency.getCode();
    }
}
