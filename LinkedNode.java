// --== CS400 Project One File Header ==--
// Name: Johnny Palumbo
// CSL Username: jpalumbo
// Email: jdpalumbo2@wisc.edu
// Lecture #: <001 @11:00am>
// Notes to Grader: <any optional extra notes to your grader>


public class LinkedNode<KeyType, ValueType> {
    private KeyType key;
    private ValueType value;

    public LinkedNode(KeyType key, ValueType value) {
        this.key = key;
        this.value = value;
    }

    public KeyType getKey() {
        return key;
    }

    public ValueType getValue() {
        return value;
    }

}
