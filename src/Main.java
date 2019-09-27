import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception{

        List<Company> companies = Reader.read();
        Printer.print(companies);


    }
}
