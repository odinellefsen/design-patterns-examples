// A simple Book class for our collection
class Book {
    private String title;
    private String author;
    
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
}

// Iterator interface from the class diagram
interface BookIterator {
    boolean hasNext();
    Book next();
    void remove();
}

// Aggregate interface from the class diagram
interface BookAggregate {
    BookIterator createIterator();
}

// ConcreteAggregate from the class diagram
class BookCollection implements BookAggregate {
    private java.util.ArrayList<Book> books;
    
    public BookCollection() {
        books = new java.util.ArrayList<Book>();
    }
    
    public void addBook(Book book) {
        books.add(book);
    }
    
    public void removeBook(Book book) {
        books.remove(book);
    }
    
    public Book getBook(int index) {
        return books.get(index);
    }
    
    public int getCount() {
        return books.size();
    }
    
    @Override
    public BookIterator createIterator() {
        return new BookCollectionIterator(this);
    }
}

// ConcreteIterator from the class diagram
class BookCollectionIterator implements BookIterator {
    private BookCollection bookCollection;
    private int currentIndex;
    private boolean removable;
    
    public BookCollectionIterator(BookCollection bookCollection) {
        this.bookCollection = bookCollection;
        this.currentIndex = 0;
        this.removable = false;
    }
    
    @Override
    public boolean hasNext() {
        return currentIndex < bookCollection.getCount();
    }
    
    @Override
    public Book next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException("No more books in the collection");
        }
        
        Book book = bookCollection.getBook(currentIndex);
        currentIndex++;
        removable = true;
        return book;
    }
    
    @Override
    public void remove() {
        if (!removable) {
            throw new IllegalStateException("You can't remove an element until you've called next()");
        }
        
        currentIndex--;
        bookCollection.removeBook(bookCollection.getBook(currentIndex));
        removable = false;
    }
}

@SuppressWarnings("all")
public class Iterator {
    public static void main(String[] args) {
        // Create a book collection
        BookCollection bookCollection = new BookCollection();
        bookCollection.addBook(new Book("Design Patterns", "Gamma, Helm, Johnson, Vlissides"));
        bookCollection.addBook(new Book("Clean Code", "Robert C. Martin"));
        bookCollection.addBook(new Book("Refactoring", "Martin Fowler"));
        bookCollection.addBook(new Book("Head First Design Patterns", "Eric Freeman, Elisabeth Robson"));
        
        // Get the iterator from the collection
        BookIterator iterator = bookCollection.createIterator();
        
        // Use the iterator to go through the collection
        System.out.println("Iterating through the book collection:");
        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println(" - " + book.getTitle() + " by " + book.getAuthor());
        }
        
        // Reset and use the iterator again
        iterator = bookCollection.createIterator();
        System.out.println("\nLooking for a specific book:");
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getTitle().contains("Clean")) {
                System.out.println("Found: " + book.getTitle());
                break;
            }
        }
        
        // Demonstrate removing an element
        iterator = bookCollection.createIterator();
        System.out.println("\nRemoving 'Refactoring' from the collection:");
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getTitle().equals("Refactoring")) {
                iterator.remove();
                System.out.println("Removed: " + book.getTitle());
            }
        }
        
        // Verify the book was removed
        iterator = bookCollection.createIterator();
        System.out.println("\nRemaining books:");
        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println(" - " + book.getTitle());
        }
    }
}