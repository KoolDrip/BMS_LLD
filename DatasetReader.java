import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatasetReader {
    
    public static List<Book> readBooksFromCSV(String fileName) {
        List<Book> books = new ArrayList<>();
        String line;
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Skip the header line
            br.readLine();
            
            while ((line = br.readLine()) != null) {
                try {
                    Book book = parseBookFromLine(line);
                    if (book != null) {
                        books.add(book);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing line: " + line);
                    System.err.println("Error message: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        
        return books;
    }
    
    private static Book parseBookFromLine(String line) {
        // Handle CSV parsing with potential commas in quoted fields
        List<String> fields = parseCSVLine(line);
        
        if (fields.size() < 7) {
            System.err.println("Incomplete data in line: " + line);
            return null;
        }
        
        try {
            String title = fields.get(0).trim();
            String author = fields.get(1).trim();
            double userRating = Double.parseDouble(fields.get(2).trim());
            int reviews = Integer.parseInt(fields.get(3).trim());
            double price = Double.parseDouble(fields.get(4).trim());
            int year = Integer.parseInt(fields.get(5).trim());
            String genre = fields.get(6).trim();
            
            return new Book(title, author, userRating, reviews, price, year, genre);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing numbers in line: " + line);
            return null;
        }
    }
    
    private static List<String> parseCSVLine(String line) {
        List<String> fields = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder field = new StringBuilder();
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(field.toString());
                field = new StringBuilder();
            } else {
                field.append(c);
            }
        }
        
        // Add the last field
        fields.add(field.toString());
        
        return fields;
    }
}