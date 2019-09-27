import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class RequestValidator {
    private String request;

    private boolean isDate() {
        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("dd.MM,yy"))
                .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yy"))
                .toFormatter();
        try {
            LocalDate.parse(request, dtf);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

//    При парсинге кодов валют я исходил из следующего описания принятой международной кодировки для бирж:
//    Таблица кодов собрана по следующему принципу: первые два символа означают государство, третий – название валюты.
//    Например, USD указывает на принадлежность к United States (США), а D – к доллару. Другой пример: JPY понимается как JP – Япония, Y – йена.
//    Данный regex также сочтёт правильными несуществующие коды, но при правильно сформированном json файле на них не будет совпадений
//    и будет выведено сообщение о возможной ошибке в написании. Конечно, можно захардкодить все валидные коды, но это довольно некрасиво, на мой взгляд.
    private boolean isCurrency() {
        Pattern p = Pattern.compile("[A-Z]{3}");
        return p.matcher(request).matches();
    }

    private boolean isExit() {
        return request.equals("exit");
    }

    public String validate(String request) {
        this.request = request;
        String result;
        if (isExit()) {
            result = "exit";
        } else if (isCurrency()) {
            result = "currency";
        } else if (isDate()) {
            result = "date";
        } else {
            result = "error";
        }
        return result;
    }
}