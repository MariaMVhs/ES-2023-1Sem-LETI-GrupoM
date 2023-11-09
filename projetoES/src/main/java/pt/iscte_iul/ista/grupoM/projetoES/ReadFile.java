package pt.iscte_iul.ista.grupoM.projetoES;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.util.List;

public class ReadFile {
    public static List<CSVRecord> readCSV(String source) throws IOException {
        Reader reader;

        if (source.startsWith("http")) {
            // If the source is a URL
            URL url = new URL(source);
            reader = new InputStreamReader(url.openStream());
        } else if (source.equals("user")) {
            // If the source is user input
            reader = new InputStreamReader(System.in);
        } else {
            // Assume it's a local file path
            reader = new FileReader(source);
        }

        try (CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';'))) {
            return parser.getRecords();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.print("Enter CSV source (URL, 'user', or local file path): ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String source = reader.readLine();

        List<CSVRecord> records = readCSV(source);

        /*
        for (CSVRecord record : records) {
            for (String value : record) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
        */
        
        writeTabulatorHTML(records);
        
    }

    
    public static void writeTabulatorHTML(List<CSVRecord> records) throws IOException {
        try (PrintWriter writer = new PrintWriter("output.html")) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<link href=\"https://unpkg.com/tabulator-tables@5.0.3/dist/css/tabulator.min.css\" rel=\"stylesheet\">");
            writer.println("<script src=\"https://unpkg.com/tabulator-tables@5.0.3/dist/js/tabulator.min.js\"></script>");
            writer.println("<title>CSV to Tabulator Table</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<h1>CSV Data in Tabulator Table</h1>");
            writer.println("<div id=\"csv-table\"></div>");
            writer.println("<script>");

            // Convert the CSV data into a format suitable for Tabulator
            writer.println("var data = [");
            for (int i = 1; i < records.size(); i++) {
                writer.println("{");
                CSVRecord record = records.get(i);
                for (int j = 0; j < record.size(); j++) {
                    String header = records.get(0).get(j);
                    String value = record.get(j);
                    writer.println("\"" + header + "\": \"" + value + "\",");
                }
                writer.println("},");
            }
            writer.println("];");

            // Initialize the Tabulator table
            writer.println("var table = new Tabulator(\"#csv-table\", {");
            writer.println("data: data,");
            writer.println("layout: \"fitDataFill\",");
            writer.println("columns: [");
            for (String header : records.get(0)) {
                writer.println("{ title: \"" + header + "\", field: \"" + header + "\" },");
            }
            writer.println("],");
            writer.println("});");
            writer.println("</script>");
            writer.println("</body>");
            writer.println("</html>");
        }

        System.out.println("html done");
        
    }
}
