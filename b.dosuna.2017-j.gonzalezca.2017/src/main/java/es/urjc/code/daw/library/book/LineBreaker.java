package es.urjc.code.daw.library.book;

import org.springframework.stereotype.Service;

@Service
public class LineBreaker {
	
	public static String breakText(String text, int lineLength) {
		if(text.length()<= lineLength) {
			return text;
		}
		else {
			final String[] lines = removeIrregularWhitespaces(text).split(" ");
			StringBuilder output = new StringBuilder();
			int charCount = 0;
			for(int i=0;i<lines.length;i++) {
				charCount += lines[i].length();
				if(charCount>lineLength) {
					if (!output.toString().equals("")) 
						output = whiteSpaceOnCharAtLineLength(output);
					lines[i] = breakWordsBiggerThanLineLength(lines[i], lineLength, false);
					output.append("\n");
					charCount=0;
				}
				output.append(lines[i]);
				output.append(" ");

			}
			return removeIrregularWhitespaces(output.toString().trim());
		}
	}

	private static StringBuilder whiteSpaceOnCharAtLineLength(StringBuilder output) {
		if(output.charAt(output.length()-1) == ' ') 
			return new StringBuilder(output.substring(0,output.length()-1));
		else
			return output;
	}
	
	private static String breakWordsBiggerThanLineLength(String word, int givenLength, boolean checker) {
		if ( (word.length()<= givenLength) && (!checker) )
			return word;
		else if (word.length()<= givenLength)
			return word + "\n";
		else {
			String aux = word.substring(0, givenLength-1);
			aux += "-\n";
			return aux + breakWordsBiggerThanLineLength(word.substring(givenLength-1), givenLength, true) ;
		}
	}
	
	private static String removeIrregularWhitespaces(String text) {
		return text.trim().replaceAll(" +", " ").replaceAll("\n\n+", "\n").replaceAll("\n ", "\n");
	}
	

}

