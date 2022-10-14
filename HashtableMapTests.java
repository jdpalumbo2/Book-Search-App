// --== CS400 Project One File Header ==--
// Name: Johnny Palumbo
// CSL Username: jpalumbo
// Email: jdpalumbo2@wisc.edu
// Lecture #: <001 @11:00am>
// Notes to Grader: <any optional extra notes to your grader>

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class contains all the test cases
 */
public class HashtableMapTests {

    /**
     * adding a new element to an empty hash table
     */
    public static boolean test1() {
        LinkedNode test = new LinkedNode(1, "val");
        HashtableMap map = new HashtableMap();
        map.put(test.getKey(), test.getValue());

        // check element is in place
        if (!map.get(1).equals("val")) {
            System.out.println("method should return \"val\" but instead returns: " + map.get(test.getKey()));
            return false;
        }

        // check size is updated accordingly
        if (map.size() != 1) {
            System.out.println("method should return a size of 1 but instead returns: " + map.size());
            return false;
        }

        map.clear();
        return true;
    }

    /**
     * testing the get method
     */
    public static boolean test2() {
        LinkedNode test = new LinkedNode(1, "val1");
        LinkedNode test2 = new LinkedNode(2, "val2");
        HashtableMap map = new HashtableMap();
        map.put(test.getKey(), test.getValue());
        map.put(test2.getKey(), test2.getValue());

        // check element is in place
        if (!map.get(test.getKey()).equals("val1")) {
            System.out.println("method should return \"val1\" but instead returns: " + map.get(test.getKey()));
            return false;
        }

        // check other element
        if (!map.get(test2.getKey()).equals("val2")) {
            System.out.println("method should return \"val2\" but instead returns: " + map.get(test2.getKey()));
            return false;
        }

        map.clear();
        return true;
    }

    /**
     * testing the remove method
     */
    public static boolean test3() {
        LinkedNode test = new LinkedNode(1, "val1");
        LinkedNode test2 = new LinkedNode(2, "val2");
        HashtableMap map = new HashtableMap();
        map.put(test.getKey(), test.getValue());
        map.put(test2.getKey(), test2.getValue());

        // check element is in place
        if (!map.get(test.getKey()).equals("val1")) {
            System.out.println("method should return \"val1\" but instead returns: " + map.get(test.getKey()));
            return false;
        }

        map.remove(test.getKey());

        // check if removed
        try {
            if (map.get(test.getKey()).equals("val1")) {
                System.out.println("method should return an exception but instead returns: " + map.get(test.getKey()));
                return false;
            }
        } catch (NoSuchElementException e) {
        }

        map.clear();
        return true;
    }

    /**
     * testing the clear() method
     */
    public static boolean test4() {
        LinkedNode test = new LinkedNode(1, "val1");
        LinkedNode test2 = new LinkedNode(2, "val2");
        HashtableMap map = new HashtableMap();
        map.put(test.getKey(), test.getValue());
        map.put(test2.getKey(), test2.getValue());

        // check element is in place
        if (!map.get(test.getKey()).equals("val1")) {
            System.out.println("method should return \"val1\" but instead returns: " + map.get(test.getKey()));
            return false;
        }

        map.clear();

        // check that the element was removed
        try {
            if (map.get(test.getKey()).equals("val1")) {
                System.out.println("method should return an exception but instead returns: " + map.get(test.getKey()));
                return false;
            }
        } catch (NoSuchElementException e) {
        }

        map.clear();
        return true;
    }

    /**
     * testing the containsKey() method
     */
    public static boolean test5() {
        LinkedNode test = new LinkedNode(1, "val1");
        LinkedNode test2 = new LinkedNode(2, "val2");
        HashtableMap map = new HashtableMap();
        map.put(test.getKey(), test.getValue());
        map.put(test2.getKey(), test2.getValue());

        // check element is in place
        if (map.containsKey(test.getKey()) != true) {
            System.out.println("method should return true but instead returns: " + map.containsKey(test.getKey()));
            return false;
        }

        // check element 2 is in place
        if (map.containsKey(test2.getKey()) != true) {
            System.out.println("method should return true but instead returns: " + map.containsKey(test2.getKey()));
            return false;
        }

        map.clear();

        // check element 2 is in place
        if (map.containsKey(test2.getKey()) != false) {
            System.out.println("method should return false but instead returns: " + map.containsKey(test2.getKey()));
            return false;
        }

        return true;
    }


public static void main(String[] args) {
    if (!test1()) {
    } else if (!test2()) {
    } else if (!test3()) {
    } else if (!test4()) {
    } else if (!test5()) {
    } else
      System.out.println("All tests passed");
    

  }

}