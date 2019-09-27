import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

public class Printer {

    public static void print(List<Company> companies) throws Exception{
        System.out.println("All companies:");
        companies.
                stream().
                forEach(company -> System.out.println(" " + company.getNameShort() + " - " + company.getEgrulDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        System.out.println("\nAll overdue securities:");
        ArrayList<String> result_old_securities = new ArrayList<String>();
        companies.
                stream().
                forEach(company -> company.getSecurities().
                        stream().
                        filter(securitie -> securitie.getDateTo().compareTo(LocalDate.now()) < 0).
                        forEach(securitie -> result_old_securities.add(" Code: " + securitie.getCode() +
                                "\n Expire date: " + securitie.getDateTo() +
                                "\n Company name: " + company.getNameFull() + "\n"))
                );
        if (result_old_securities.size() == 0) {
            System.out.println("None found");
        } else {
            System.out.println(result_old_securities.size() + " found");
            for (String s : result_old_securities) {
                System.out.println(s);
            }
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean exit_flag = true;
        while (exit_flag) {
            System.out.println("\nPlease enter request:");
            String request = reader.readLine();
            String requestType = new RequestValidator().validate(request);
            switch (requestType) {
                case "date":
                    ArrayList<String> result_date = new ArrayList<String>();
                    DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                            .appendOptional(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                            .appendOptional(DateTimeFormatter.ofPattern("dd.MM,yy"))
                            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yy"))
                            .toFormatter();
                    LocalDate date_request = LocalDate.parse(request, dtf);
                    System.out.println("All companies established after date:");
                    companies.
                            stream().
                            filter(company -> company.getEgrulDate().compareTo(date_request) > 0).
                            forEach(company -> result_date.add(" Company name: " + company.getNameFull() + ", date of establishment: " + company.getEgrulDate()));
                    if (result_date.size() == 0) {
                        System.out.println("None found");
                    } else {
                        for (String s : result_date) {
                            System.out.println(s);
                        }
                    }
                    break;
                case "currency":
                    ArrayList<String> result_currency = new ArrayList<String>();
                    System.out.println("All securities with this currency:");
                    companies.
                            stream().
                            forEach(company -> company.getSecurities().
                                    stream().
                                    filter(securitie -> securitie.getCurrencyCode().equals(request)).
                                    forEach(securitie -> result_currency.add(" id: " + securitie.getId() + ", code: " + securitie.getCode()))
                            );
                    if (result_currency.size() == 0) {
                        System.out.println("None found or wrong currency code");
                    } else {
                        for (String s : result_currency) {
                            System.out.println(s);
                        }
                    }
                    break;
                case "exit":
                    exit_flag = false;
                    System.out.println("Exiting program...");
                    break;
                case "error":
                    System.out.println("Error: wrong request.\nList of possible requests:\n  " +
                            "Date:\n    dd.MM.yyyy\n    dd.MM,yy\n    dd/MM/yyyy\n    dd/MM/yy\n  " +
                            "Currency: 3 uppercase english letters\n  exit to close the program");
                    break;
                default:
                    break;
            }
        }
    }
}