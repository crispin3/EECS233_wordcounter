public class HashNode {
	
	// Initialize variables
	private String word;
	private int count;
	
	// Constructor for word and count
	public HashNode(String word, int count) {
		this.word = word;
		this.count = count;;
	}
	
	// Get count of word
	public int getCount(){
		return count;
	}
	
	// Get word
	public String getWord(){
		return word;
	}
	
	// Increase count of count
	public void incCount() {
		count++;
	}
}
