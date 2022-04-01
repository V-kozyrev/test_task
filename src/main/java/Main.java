import com.opencsv.exceptions.CsvException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Введите строку:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CSVProcessing csvProcessing;
        try {
            String substring = reader.readLine();
            long start = System.currentTimeMillis();
            if (args.length != 0){
                 csvProcessing = new CSVProcessing(substring, Integer.parseInt(args[0]));
            }
            else
                 csvProcessing = new CSVProcessing(substring);
            csvProcessing.processingFileToOpenCSV();
            System.out.println("Количество найденных строк: " + csvProcessing.getNumberOfLinesFound() +
                    "  Время, затраченное на поиск: " + (System.currentTimeMillis() - start));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }
}