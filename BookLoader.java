import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * This class houses the SongLoader class and its methods
 */

public class BookLoader implements IBookLoader { 


    /**
     * This method loads the list of books from a CSV file.
     * @param filepathToCSV path to the CSV file relative to the executable
     * @return a list of book objects
     * @throws FileNotFoundException
     */
    public List<IBook> loadBooks(String filepathToCSV) throws FileNotFoundException {
        Scanner scnr = new Scanner(new File(filepathToCSV));
        List<IBook> books = new LinkedList<>();
        scnr.nextLine();
        while (scnr.hasNextLine()) {
            books.add(addBook(scnr.nextLine()));
        }
        return books;
    }

    /**
     * This method helps place data from csv file into a new book object
     * @param line path to the CSV file relative to the executable
     * @return a book with infofrom csv line
     */
    private IBook addBook(String line){
        String[] bits = (line.split("\\s*,\\s*"));
        //System.out.println(line);
        String title;
        String authors;
        String isbn;

        try{
        title = bits[1];
        authors = bits[2];
        isbn = bits[5];
        } catch (IndexOutOfBoundsException e) {
            title = "fail";
            authors = "fail";
            isbn = "fail";
        }
        IBook book =  new Book(title, authors, isbn);
        return book;
    }

}
