import java.util.*;

public class BookAnalyzer {
    private List<Book> books;
    
    public BookAnalyzer(List<Book> books) {
        this.books = books;
    }
    
    // Task 1: Total number of books by an author
    public int getTotalBooksByAuthor(String authorName) {
        int count = 0;
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(authorName)) {
                count++;
            }
        }
        return count;
    }
    
    // Task 2: All the authors in the dataset
    public Set<String> getAllAuthors() {
        Set<String> authors = new HashSet<>();
        for (Book book : books) {
            authors.add(book.getAuthor());
        }
        return authors;
    }
    
    public void printAllAuthors() {
        Set<String> authors = getAllAuthors();
        System.out.println("All authors in the dataset:");
        System.out.println("---------------------------");
        for (String author : authors) {
            System.out.println(author);
        }
        System.out.println("Total number of unique authors: " + authors.size());
    }
    
    // Task 3: Names of all the books by an author
    public List<String> getBooksByAuthor(String authorName) {
        List<String> bookTitles = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(authorName)) {
                bookTitles.add(book.getTitle());
            }
        }
        return bookTitles;
    }
    
    public void printBooksByAuthor(String authorName) {
        List<String> bookTitles = getBooksByAuthor(authorName);
        System.out.println("Books by " + authorName + ":");
        System.out.println("---------------------------");
        if (bookTitles.isEmpty()) {
            System.out.println("No books found for author: " + authorName);
        } else {
            for (String title : bookTitles) {
                System.out.println("- " + title);
            }
        }
        System.out.println("Total books: " + bookTitles.size());
    }
    
    // Task 4: Classify with a user rating
    public List<Book> getBooksByRating(double rating) {
        List<Book> booksWithRating = new ArrayList<>();
        for (Book book : books) {
            if (book.getUserRating() == rating) {
                booksWithRating.add(book);
            }
        }
        return booksWithRating;
    }
    
    public void printBooksByRating(double rating) {
        List<Book> booksWithRating = getBooksByRating(rating);
        System.out.println("Books with rating " + rating + ":");
        System.out.println("---------------------------");
        if (booksWithRating.isEmpty()) {
            System.out.println("No books found with rating: " + rating);
        } else {
            for (Book book : booksWithRating) {
                System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
            }
        }
        System.out.println("Total books: " + booksWithRating.size());
    }
    
    // Task 5: Price of all the books by an author
    public Map<String, Double> getBooksAndPricesByAuthor(String authorName) {
        Map<String, Double> bookPrices = new LinkedHashMap<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(authorName)) {
                bookPrices.put(book.getTitle(), book.getPrice());
            }
        }
        return bookPrices;
    }
    
    public void printBooksAndPricesByAuthor(String authorName) {
        Map<String, Double> bookPrices = getBooksAndPricesByAuthor(authorName);
        System.out.println("Books and prices by " + authorName + ":");
        System.out.println("------------------------------------------");
        if (bookPrices.isEmpty()) {
            System.out.println("No books found for author: " + authorName);
        } else {
            for (Map.Entry<String, Double> entry : bookPrices.entrySet()) {
                System.out.println("- " + entry.getKey() + " : $" + entry.getValue());
            }
        }
        System.out.println("Total books: " + bookPrices.size());
    }
    
    // Additional utility methods
    public void printDatasetSummary() {
        System.out.println("Dataset Summary:");
        System.out.println("================");
        System.out.println("Total books: " + books.size());
        System.out.println("Total unique authors: " + getAllAuthors().size());
        
        // Count fiction vs non-fiction
        int fictionCount = 0;
        int nonFictionCount = 0;
        for (Book book : books) {
            if (book.getGenre().equalsIgnoreCase("Fiction")) {
                fictionCount++;
            } else {
                nonFictionCount++;
            }
        }
        System.out.println("Fiction books: " + fictionCount);
        System.out.println("Non-fiction books: " + nonFictionCount);
        
        // Find rating range
        double minRating = books.stream().mapToDouble(Book::getUserRating).min().orElse(0);
        double maxRating = books.stream().mapToDouble(Book::getUserRating).max().orElse(0);
        System.out.println("Rating range: " + minRating + " - " + maxRating);
        
        // Find price range
        double minPrice = books.stream().mapToDouble(Book::getPrice).min().orElse(0);
        double maxPrice = books.stream().mapToDouble(Book::getPrice).max().orElse(0);
        System.out.println("Price range: $" + minPrice + " - $" + maxPrice);
    }
}