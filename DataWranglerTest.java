import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.io.FileNotFoundException;

public class DataWranglerTest {

    public static void main(String[] args) throws Exception {
        if (test1()) {
            System.out.println("DataWrangler Individual Test 1: passed");
        } else {
            System.out.println("DataWrangler Individual Test 1: failed");
        }

        if (test2()) {
            System.out.println("DataWrangler Individual Test 2: passed");
        } else {
            System.out.println("DataWrangler Individual Test 2: failed");
        }

        if (test3()) {
            System.out.println("DataWrangler Individual Test 3: passed");
        } else {
            System.out.println("DataWrangler Individual Test 3: failed");
        }

        if (test4()) {
            System.out.println("DataWrangler Individual Test 4: passed");
        } else {
            System.out.println("DataWrangler Individual Test 4: failed");
        }

        if (test5()) {
            System.out.println("DataWrangler Individual Test 5: passed");
        } else {
            System.out.println("DataWrangler Individual Test 5: failed");
        }

        if (test6()) {
            System.out.println("DataWrangler Integration Test 1: passed");
        } else {
            System.out.println("DataWrangler Integration Test 1: failed");
        }

        if (test7()) {
            System.out.println("DataWrangler Integration Test 2: passed");
        } else {
            System.out.println("DataWrangler Integration Test 2: failed");
        }

        if (test8()) {
            System.out.println("DataWrangler Partner (BackendDeveloper) Test 1: passed");
        } else {
            System.out.println("DataWrangler Partner (BackendDeveloper) Test 1: failed");
        }

        if (test9()) {
            System.out.println("DataWrangler Partner (BackendDeveloper) Test 2: passed");
        } else {
            System.out.println("DataWrangler Partner (BackendDeveloper) Test 2: failed");
        }

    }

    /**
     * Tests the title sorting ensuring that the book loader corrected read from
     * csv file
     * as well as checking the data was correctly stored and accessable through the
     * getTitle method
     * 
     * @return true if correct, false otherwise
     * @throws FileNotFoundException
     */
    public static boolean test1() throws FileNotFoundException {
        BookLoader load = new BookLoader();
        List<IBook> list = load.loadBooks("books.csv");
        if (!list.get(1).getTitle().equals("Harry Potter and the Order of the Phoenix (Harry Potter  #5)")) {
            return false;
        }
        return true;
    }

    /**
     * Tests the authors sorting ensuring that the book loader corrected read from
     * csv file
     * as well as checking the data was correctly stored and accessable through the
     * getAuthors method
     * 
     * @return true if correct, false otherwise
     * @throws FileNotFoundException
     */
    public static boolean test2() throws FileNotFoundException {
        BookLoader load = new BookLoader();
        List<IBook> list = load.loadBooks("books.csv");
        // System.out.println(list.get(2).getAuthors());
        if (!list.get(2).getAuthors().equals("J.K. Rowling")) {
            return false;
        }
        return true;
    }

    /**
     * Tests the isbn sorting ensuring that the book loader corrected read from
     * csv file
     * as well as checking the data was correctly stored and accessable through the
     * getISBN13 method
     * 
     * @return true if correct, false otherwise
     * @throws FileNotFoundException
     */
    public static boolean test3() throws FileNotFoundException {
        BookLoader load = new BookLoader();
        List<IBook> list = load.loadBooks("books.csv");
        // System.out.println(list.get(3).getISBN13());
        if (!list.get(3).getISBN13().equals("9780439655484")) {
            return false;
        }
        return true;
    }

    /**
     * Tests the authors sorting ensuring that the book loader corrected read from
     * csv file for separate lines, bouncing back false if the same author was
     * repeated
     * 
     * @return true if correct, false otherwise
     * @throws FileNotFoundException
     */
    public static boolean test4() throws FileNotFoundException {
        BookLoader load = new BookLoader();
        List<IBook> list = load.loadBooks("books.csv");
        if (list.get(2).getAuthors().equals(list.get(1).getAuthors())) {
            return false;
        }
        return true;
    }

    /**
     * checks the first location of the linked list to ensure that the headers of
     * the csv file were not mistaken for actual books
     * 
     * @return true if correct, false otherwise
     * @throws FileNotFoundException
     */
    public static boolean test5() throws FileNotFoundException {
        BookLoader load = new BookLoader();
        List<IBook> list = load.loadBooks("books.csv");
        // System.out.println(list.get(0).getTitle());
        if (list.get(0).getTitle().equals("title")) {
            return false;
        }
        return true;
    }

    /**
     * DataWrangler Integration test 1
     * 
     * @return true if correct, false otherwise
     * @throws FileNotFoundException
     */
    public static boolean test6() throws FileNotFoundException {
    BookLoader loader = new BookLoader();
    BookMapperBackend mapper = new BookMapperBackend();
    IISBNValidator checker = new ISBNChecker();


    List<IBook> list = loader.loadBooks("books.csv");
    for (int i = 0; i < list.size(); i++) {
        mapper.addBook((Book) list.get(i));
    }

    Scanner userInputScanner = new Scanner("1\n9780618517657\n4\n");
    TextUITester tester = new TextUITester("1\n9780618517657\n4\n");

    IBookMapperFrontend frontend = new BookMapperFrontend(userInputScanner, mapper, checker);
    frontend.runCommandLoop();
    String output = tester.checkOutput();

    if (output.contains("You are in the Lookup ISBN Menu:") &&
        output.contains("1. \"The Lord of the Rings (The Lord of the Rings  #1-3)\"" +
            " by J.R.R. Tolkien, ISBN: 9780618517657")) {
    } else {
      System.out.println("error 1: ISBN lookup function does not work correctly");
      return false;
    }
    return true;
    }

    /**
     * DataWrangler Integration test 2
     * 
     * @return true if correct, false otherwise
     * @throws FileNotFoundException
     */
    public static boolean test7() throws FileNotFoundException {
        {
            BookLoader loader = new BookLoader();
            BookMapperBackend mapper = new BookMapperBackend();
            IISBNValidator checker = new ISBNChecker();
        
        
            List<IBook> list = loader.loadBooks("books.csv");
            for (int i = 0; i < list.size(); i++) {
                mapper.addBook((Book) list.get(i));
            }
        
            Scanner userInputScanner = new Scanner("1\ninvalid\n4\n");
            TextUITester tester = new TextUITester("1\ninvalid\n4\n");
        
            IBookMapperFrontend frontend = new BookMapperFrontend(userInputScanner, mapper, checker);
            frontend.runCommandLoop();
            String output = tester.checkOutput();
        
            if (output.contains("You are in the Search for Title Word Menu:") &&
                output.contains("That book isn't in the database.")) {
            } else {
              System.out.println("error 2: Search title function did not work properly");
              return false;
            }
            return true;
            }
    }

    /**
     * DataWrangler Partner (BackendDeveloper) Test 1
     * 
     * @return true if correct, false otherwise
     * @throws FileNotFoundException
     */
    public static boolean test8() throws FileNotFoundException {
        try {
            BookMapperBackend bookMapperBackend = new BookMapperBackend();
            Book ibook = new Book("Harry Potter", "JK Rowling", "12345678");
            bookMapperBackend.addBook(ibook);
            if (bookMapperBackend.getNumberOfBooks() != 1) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * DataWrangler Partner (BackendDeveloper) Test 2
     * 
     * @return true if correct, false otherwise
     * @throws FileNotFoundException
     */
    public static boolean test9() throws FileNotFoundException {
        try {
            Book ibook = new Book("Harry Potter", "JK Rowling", "12345678");
            BookMapperBackend bookMapperBackend = new BookMapperBackend();
            String key = ibook.getISBN13();
            bookMapperBackend.addBook(ibook);
            if (bookMapperBackend.getByISBN(key).equals(ibook)) {
                return true;
            }
            System.out.println(bookMapperBackend.getByISBN(key));
            return false;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
