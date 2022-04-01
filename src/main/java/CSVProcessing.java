import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class CSVProcessing {
    private int numberOfLinesFound = 0;
    private int searchColumn = 1;
    private String substringToSearch;
    private final String fileLocation = ".\\src\\main\\resources\\airports.dat";

    CSVProcessing(String substringToSearch) {
        this.substringToSearch = substringToSearch;
    }

    CSVProcessing(String substringToSearch, int searchColumn) {
        this.substringToSearch = substringToSearch;
        this.searchColumn = searchColumn;
    }

    int getNumberOfLinesFound() {
        return this.numberOfLinesFound;
    }

    private String stringAssembly(String[] lines) {
        StringBuilder outputLine = new StringBuilder();
        outputLine.append(lines[this.searchColumn]).append(" (");
        for (int idx = 0; idx < lines.length - 1; idx++) {
            if (idx == this.searchColumn)
                continue;
            outputLine.append(lines[idx]).append(", ");
        }
        if (lines.length - 1 != this.searchColumn)
            outputLine.append(lines[lines.length - 1]).append(")");
        else
            outputLine.replace(outputLine.length() - 2, outputLine.length(), ")");
        return outputLine.toString();
    }

    private void printLines(ArrayList<String> allOutputLine) {
        for (String line : allOutputLine) {
            System.out.println(line);
        }
    }

    void processingFileToOpenCSV() throws IOException, CsvException {
        FileReader fileReader;
        try {
            fileReader = new FileReader(this.fileLocation);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
        CSVReader reader = new CSVReader(fileReader);
        String[] lines;
        ArrayList<String> allOutputLine = new ArrayList<String>();
        try {
            while ((lines = reader.readNext()) != null) {
                if (substringSearch(this.substringToSearch, lines[this.searchColumn])) {
                    allOutputLine.add(stringAssembly(lines));
                    this.numberOfLinesFound++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (CsvException e) {
            e.printStackTrace();
            throw e;
        }
        Collections.sort(allOutputLine);
        printLines(allOutputLine);
    }

    private Boolean substringSearch(String substring, String str) {
        return str.substring(0, substring.length()).equals(substring);
    }
}
