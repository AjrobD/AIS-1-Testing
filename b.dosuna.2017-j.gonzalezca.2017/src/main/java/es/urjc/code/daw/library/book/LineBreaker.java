package es.urjc.code.daw.library.book;

import org.springframework.stereotype.Service;

@Service
public class LineBreaker {
	
	
	private static String multipleChecker(String word, int givenLength) {
		if (word.length() <= givenLength) {
			return word;
		}
		else {
			String aux = word.substring(0, givenLength-1);
			aux += "-\n";
			return aux += multipleChecker( word.substring(givenLength-1, word.length()), givenLength) ;
		}
	}
	
	
	private static int specialTreatment(String[] wordsArray, int givenLength) {
		String output = "";
		for (int k=0; ( k < wordsArray.length-1 && output.length() < givenLength ); k++) {
			output += wordsArray[k];
			output += " ";
		}
		if (output.charAt(givenLength) == ' ') {
			return ((output.length() - 1) / givenLength);
		}
		else return 0;
	}
	
	
	public static String breakText(String text, int lineLength) {
		String aux = text.trim().replaceAll(" +", " ");
		String output = "";
		String[] lines = aux.split(" ");
		int wordNumber = lines.length;
		for(int i=0; i<wordNumber; i++) {
			output += multipleChecker(lines[i], lineLength);
			output += "\n";
		}
		return output.substring(0, output.length()-1);	
	}
	
	
}


