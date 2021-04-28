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
	
	
	public static String breakText(String text, int lineLength) {
		
		String aux = text.trim().replaceAll(" +", " ");
		String[] lines = aux.split(" ");
		String output = "";
		int wordNumber = lines.length;
		for(int i=0; i<wordNumber; i++) {
			output += multipleChecker(lines[i], lineLength);
			output += "\n";
		}
		return output.substring(0, output.length()-1);	
	}
	
	
}


