
/**
 * This class houses the Book Object.
 */
public class Book implements IBook {
    private String title;
    private String authors;
    private String isbn;

    /**
     * The constructor for book class
     * 
     * @param title   title of the song
     * @param authors author of the book
     * @param isbn    unique book code
     */
    public Book(String title, String authors, String isbn) {
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
    }

    /**
     * Gets the title of the book
     * 
     * @return title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the authors of the book
     * 
     * @return authors of the book
     */
    public String getAuthors() {
        return authors;
    }

    /**
     * Gets the isbn of the book
     * 
     * @return isbn of the book
     */
    public String getISBN13() {
        return isbn;
    }
}
