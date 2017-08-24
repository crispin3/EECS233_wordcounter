import java.util.*;
import java.io.*;

//this class has the method that counts the words as well as the main method//
public class WordCounter {

	public static String wordCount(String input_file, String output_file) throws Exception{

		// Instantiate hashtable
		Map table = new Map();
		File outputFile = new File(output_file);
		File inputFile = new File(input_file);

		// Create input and output file reader
		FileWriter fw = new FileWriter(outputFile.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		BufferedReader br = new BufferedReader(new FileReader(inputFile));

		// Stores current line from inputfile
		String currentLine = br.readLine();

		// If line is not empty, delimit the line
		while(currentLine!= null){
			// Splits word and delimits
			String [] words  = currentLine.toLowerCase().split("[^0-9a-zA-Z]");

			// Add words in string array to hashtable
			for(int i = 0; i < words.length; i++){
				table.add(words[i]);
			}

			// Go to next line in input file
			currentLine = br.readLine();
		}

		// Writes the word and count of the hashnodes in arraylist in array
		for(int i = 0; i < table.getTableSize(); i++){
			if(table.getTable() != null){
				ArrayList<HashNode> temp = table.getTable()[i];
				for(HashNode t : temp) {
					bw.write("(" + t.getWord() + " " + String.valueOf(t.getCount()) + ") ");
				}
			}
		}

		// Closes reader and writer
		bw.close();
		br.close();
		
		// Calculate average number of collisions per word in the table
		double avgCollision = table.getCollisions() / table.getTableSize();
		// Prints table size, total number of unique words, and average number of collisions
		// Second prints with total number of collisions
		System.out.println("Everything worked fine! " + "Total table size: " + table.getTableSize() + " Total number of unique words: " + table.getUniqueWords() + " Average number of collisions: " + avgCollision);
		System.out.println("Total number of collisions: " + table.getCollisions());
		return ("We are done");
	}


	// Puts in the input.txt and output.txt into wordcount
	public static void main(String[] args) throws Exception{
		try {
			WordCounter.wordCount("input.txt", "output.txt");
		}
		catch(FileNotFoundException e) {
			System.out.println("Something happened in main argument, but WHAT!");
		}
	}
}






