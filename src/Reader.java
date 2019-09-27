import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {

    public static List<Company> read() throws Exception{
        String filepath = "test.json";
        File read_file = new File(filepath);
        Scanner input;
        try {
            input = new Scanner(read_file);
        } catch (FileNotFoundException e) {
            System.err.println("Error: no such file. ");
            throw new Error();
        }

        final Type COMPANY_LIST_TYPE = new TypeToken<List<Company>>(){}.getType();
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(filepath));
        ArrayList<Company> companies = new ArrayList<>();

        //На случай, если на вход подаётся только одна компания без квадратных скобок (не массив)
        try {
            companies.add(gson.fromJson(reader, Company.class));
        } catch (JsonSyntaxException e) {

        }
        //Штатный случай с массивом json объектов, иначе выкинет warning, но работать продолжит
        try {
            companies = gson.fromJson(reader, COMPANY_LIST_TYPE);
        } catch (JsonSyntaxException e) {
            System.err.println("Warning: file isn't json array and contains only one element");
        }

        try {
            for (Company company : companies) {
                //На случай отсутствия критичной для выполнения задания информации в json объектах (все обрабатываемые в Printer поля)
                if (!company.check()) {
                    System.err.println("Error: file is damaged, important information is missing");
                    throw new Error();
                }
                company.improve();
            }
            //На случай пустого файла
        } catch (NullPointerException e) {
            System.err.println("Error: file is empty");
            throw new Error();
        }
        return companies;
    }
}

