import java.util.ArrayList;
import java.util.Iterator;

public class Map {
	
	// Initialize variables
	private int tableSize;
	private int currentSize = 0;
	private int collisions = 0;
	private int uniqueWords = 0;
	// Create array of arraylist of hashnodes
	private ArrayList<HashNode>[] table;
	
	// Constructor
	public Map() {
		tableSize = 64;
		table = new ArrayList[tableSize];
		// Initialize arraylist of hashnodes in each array
		for(int i = 0; i < tableSize; i++) {
			table[i] = new ArrayList<HashNode>();
		}
	}
	
	// Get tablesize method
	public int getTableSize() {
		return tableSize;
	}
	
	// Get current filled size of array
	public int getCurrentSize() {
		return currentSize;
	}
	
	// Get collisions
	public int getCollisions() {
		return collisions;
	}
	
	// Get table
	public ArrayList<HashNode>[] getTable() {
		return table;
	}
	
	// Return unique number of words
	public int getUniqueWords() {
		return uniqueWords;
	}
	
	//Increment currentSize
	public void incCurrentSize() {
		currentSize++;
	}
	
	// Increment collisions
	public void incCollisions() {
		collisions++;
	}
	
	// Increment unique words
	public void incUniqueWords() {
		uniqueWords++;
	}

	// Method to get modulus
	private int myHash(String word) {
		int hashCode = word.hashCode();
		int hashVal = Math.abs(hashCode % tableSize);
		return hashVal;
	}
	
	// Method to add elements into array
	public void add(String word) {
		
		// Get hashcode and start collision counter
		int index = this.myHash(word);
		int collisionCount = 0;
		boolean wordFound = false;
		
		//Expand if hashCode is bigger than the table
		if (index > getTableSize()) {
			rehash();
		}
		
		// Add HashNode arraylist is empty
		if (table[index].isEmpty()) {
			table[index].add(new HashNode(word, 1)); // add word and 1
			incCurrentSize();
			incUniqueWords();
		}
		else if (wordFound == false) {
			// Traverse arraylist in element to see if word is present and increment
			for (int i = 0; i < table[index].size(); i++) {
			if (table[index].get(i).getWord().equals(word)) { 
				table[index].get(i).incCount();
				wordFound = true;
				break;
			}
			else {
				// Increase collision count
				collisionCount++;
			}
			}
		}
		// If word has not been found, add word in the arraylist
		else if (wordFound == false) {
			table[index].add(new HashNode(word, 1));
			this.collisions = this.collisions + collisionCount;
		}
		// If 99% of table is filled, rehash
		if ((this.getCurrentSize() / this.getTableSize()) > 0.99) {
			rehash();
		}
	}
	
	public void rehash() {
		// Store old and new tableSize to variable
		int oldTableSize = this.getTableSize();
		int newTableSize = (this.getTableSize() * 2);
		// Change table size
		this.tableSize = newTableSize;
		// Create temp hashmap of arraylist
		ArrayList<HashNode>[] temp = new ArrayList[newTableSize];
		// Instantiate arraylist<hashnode> in each element of the temp array
		for (int i = 0; i < temp.length; i++) {
			temp[i] = new ArrayList<HashNode>();
		}
		// Iterate through table and find not empty arraylist
		for (int l = 0; l < table.length; l++) {
			// Get elements from table, calculate new hashCode value
			// And insert into temp
			if (!table[l].isEmpty()) {
				for (int s = 0; s < table[l].size(); s++) {
				String newPosWord = table[l].get(s).getWord();
				int newPosCount = table[l].get(s).getCount();
				int newIndex = myHash(table[l].get(s).getWord());
				temp[newIndex].add(new HashNode(newPosWord, newPosCount));
				}
			}
		}
		table = temp;
		}
	}