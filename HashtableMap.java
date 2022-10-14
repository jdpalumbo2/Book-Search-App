// --== CS400 Project One File Header ==--
// Name: Johnny Palumbo
// CSL Username: jpalumbo
// Email: jdpalumbo2@wisc.edu
// Lecture #: <001 @11:00am>
// Notes to Grader: <any optional extra notes to your grader>

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

	protected Object[] table;
	private int capacity;

	/**
	 * This constructor makes a new map with the capacity given
	 * 
	 * @param capacity the number of spaces to put in the hashtable
	 */
	public HashtableMap(int capacity) {
		this.capacity = capacity;
		this.table = new Object[capacity];
	}

	/**
	 * This constructor makes a new map with default capacity 15
	 */
	public HashtableMap() { // with default capacity = 15
		this.capacity = 15;
		this.table = new Object[15];
	}

	/**
	 * helper method that calculates the load factor
	 * 
	 * @return the load factor
	 */
	private float getLoadFactor() {
		return ((float) size() / (float) capacity);
	}

	/**
	 * helper method to make a new hash table of double size and reput each pair in
	 */
	private void rehash() {
		Object[] temp = table;
		this.capacity *= 2;

		//this.table = new Object[capacity];
		clear();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] != null) {
				LinkedList<LinkedNode<KeyType, ValueType>> list = (LinkedList<LinkedNode<KeyType, ValueType>>) temp[i];
				for (int j = 0; j < list.size(); j++) {
					put(list.get(j).getKey(), list.get(j).getValue());
				}
			}
		}
	}

	/**
	 * Inserts a new (key, value) pair into the map if the map does not
	 * contain a value mapped to key yet.
	 * 
	 * @param key   the key of the (key, value) pair to store
	 * @param value the value that the key will map to
	 * @return true if the (key, value) pair was inserted into the map,
	 *         false if a mapping for key already exists and the
	 *         new (key, value) pair could not be inserted
	 */
	@Override
	public boolean put(KeyType key, ValueType value) {
		if (key.equals(null))
			return false;

		if (containsKey(key))
			return false;

		int index = Math.abs(key.hashCode()) % capacity;

		if (table[index] == null) {
			LinkedList<LinkedNode<KeyType, ValueType>> list = new LinkedList<LinkedNode<KeyType, ValueType>>();
			this.table[index] = list;
			list.add(new LinkedNode<>(key, value));
			return true;
		}

		else if (table[index] != null)
		{
			LinkedList<LinkedNode<KeyType, ValueType>> list = (LinkedList<LinkedNode<KeyType, ValueType>>) this.table[index];
			list.add(new LinkedNode<>(key, value));

			if (getLoadFactor() >= .7) {
				rehash();
			}
			return true;
		}
		return false;
	}

	/**
	 * Returns the value mapped to a key if the map contains such a mapping.
	 * 
	 * @param key the key for which to look up the value
	 * @return the value mapped to the key
	 * @throws NoSuchElementException if the map does not contain a mapping
	 *                                for the key
	 */
	public ValueType get(KeyType key) throws NoSuchElementException {

		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				LinkedList<LinkedNode<KeyType, ValueType>> list = (LinkedList<LinkedNode<KeyType, ValueType>>) this.table[i];
				for (int j = 0; j < list.size(); j++) {
					if (list.get(j).getKey().equals(key)) {
						return list.get(j).getValue();
					}
				}
			}
		}
		throw new NoSuchElementException("map does not contain a mapping for the key");
	}

	/**
	 * Removes a key and its value from the map.
	 * 
	 * @param key the key for the (key, value) pair to remove
	 * @return the value for the (key, value) pair that was removed,
	 *         or null if the map did not contain a mapping for key
	 */
	public ValueType remove(KeyType key) {
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				LinkedList<LinkedNode<KeyType, ValueType>> list = (LinkedList<LinkedNode<KeyType, ValueType>>) table[i];
				for (int j = 0; j < list.size(); j++) {
					if (list.get(j).getKey().equals(key)) {
						ValueType ret = list.get(j).getValue();
						list.remove(j);
						return ret;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Checks if a key is stored in the map.
	 * 
	 * @param key the key to check for
	 * @return true if the key is stored (mapped to a value) by the map
	 *         and false otherwise
	 */
	public boolean containsKey(KeyType key) {

		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				LinkedList<LinkedNode<KeyType, ValueType>> list = (LinkedList<LinkedNode<KeyType, ValueType>>) table[i];
				for (int j = 0; j < list.size(); j++) {
					if (list.get(j).getKey().equals(key)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Returns the number of (key, value) pairs stored in the map.
	 * 
	 * @return the number of (key, value) pairs stored in the map
	 */
	public int size() {
		int size = 0;

		for (int i = 0; i < this.table.length; i++) {
			if (this.table[i] != null) {
				LinkedList<LinkedNode<KeyType, ValueType>> list = (LinkedList<LinkedNode<KeyType, ValueType>>) this.table[i];
				for (int j = 0; j < list.size(); j++) {
					if (!(list.get(j).equals(null))) {
						size++;
					}
				}
			}
		}

		return size;
	}

	/**
	 * Removes all (key, value) pairs from the map.
	 */
	public void clear() {
		this.table = new Object[capacity];
	}

}