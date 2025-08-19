import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        // Read the dataset
        String fileName = "dataset/data.csv";
        List<Book> books = DatasetReader.readBooksFromCSV(fileName);
        
        if (books.isEmpty()) {
            System.out.println("No books were loaded. Please check your CSV file.");
            return;
        }
        
        System.out.println("Successfully loaded " + books.size() + " books from the dataset.");
        
        // Create BookAnalyzer instance
        BookAnalyzer analyzer = new BookAnalyzer(books);
        
        // Display menu and handle user input
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            displayMenu();
            System.out.print("Enter your choice (1-7): ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        handleTotalBooksByAuthor(scanner, analyzer);
                        break;
                    case 2:
                        analyzer.printAllAuthors();
                        break;
                    case 3:
                        handleBooksByAuthor(scanner, analyzer);
                        break;
                    case 4:
                        handleBooksByRating(scanner, analyzer);
                        break;
                    case 5:
                        handleBooksAndPricesByAuthor(scanner, analyzer);
                        break;
                    case 6:
                        analyzer.printDatasetSummary();
                        break;
                    case 7:
                        running = false;
                        System.out.println("Thank you for using the Book Analyzer!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                }
                
                if (running) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
                
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        
        scanner.close();
    }
    
    private static void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           AMAZON BESTSELLERS ANALYZER");
        System.out.println("=".repeat(50));
        System.out.println("1. Get total number of books by an author");
        System.out.println("2. Display all authors in the dataset");
        System.out.println("3. Get all books by an author");
        System.out.println("4. Get books by user rating");
        System.out.println("5. Get book prices by an author");
        System.out.println("6. Display dataset summary");
        System.out.println("7. Exit");
        System.out.println("=".repeat(50));
    }
    
    private static void handleTotalBooksByAuthor(Scanner scanner, BookAnalyzer analyzer) {
        System.out.print("Enter author name: ");
        String authorName = scanner.nextLine().trim();
        
        if (!authorName.isEmpty()) {
            int totalBooks = analyzer.getTotalBooksByAuthor(authorName);
            System.out.println("\nAuthor: " + authorName);
            System.out.println("Total books: " + totalBooks);
        } else {
            System.out.println("Author name cannot be empty.");
        }
    }
    
    private static void handleBooksByAuthor(Scanner scanner, BookAnalyzer analyzer) {
        System.out.print("Enter author name: ");
        String authorName = scanner.nextLine().trim();
        
        if (!authorName.isEmpty()) {
            System.out.println();
            analyzer.printBooksByAuthor(authorName);
        } else {
            System.out.println("Author name cannot be empty.");
        }
    }
    
    private static void handleBooksByRating(Scanner scanner, BookAnalyzer analyzer) {
        System.out.print("Enter user rating (e.g., 4.7): ");
        
        try {
            double rating = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            
            System.out.println();
            analyzer.printBooksByRating(rating);
        } catch (Exception e) {
            System.out.println("Invalid rating format. Please enter a decimal number.");
            scanner.nextLine(); // Clear invalid input
        }
    }
    
    private static void handleBooksAndPricesByAuthor(Scanner scanner, BookAnalyzer analyzer) {
        System.out.print("Enter author name: ");
        String authorName = scanner.nextLine().trim();
        
        if (!authorName.isEmpty()) {
            System.out.println();
            analyzer.printBooksAndPricesByAuthor(authorName);
        } else {
            System.out.println("Author name cannot be empty.");
        }
    }
}