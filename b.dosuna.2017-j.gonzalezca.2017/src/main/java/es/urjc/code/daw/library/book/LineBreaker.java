package es.urjc.code.daw.library.book;

import org.springframework.stereotype.Service;

@Service
public class LineBreaker {
	
	public static String breakText(String text, int lineLength) {
		if(text.length()<= lineLength) {
			return text;
		}
		else {
			final String[] lines = eraseIrregularWhitespaces(text);
			StringBuilder output = new StringBuilder();
			int charCount = 0;
			for(int i=0;i<lines.length;i++) {
				charCount += lines[i].length();
				if(charCount>lineLength) {
					if (!output.toString().equals("")) {
						output = whiteSpaceOnCharAtLineLength(output);
					} else
						lines[i] = breakWordsBiggerThanLineLength(lines[i], lineLength);
					output.append("\n");
					charCount=0;
				}
				output.append(lines[i]);
				if(i <lines.length-1)
					output.append(" ");
			}
			return output.toString().trim();
		}
	}

	private static StringBuilder whiteSpaceOnCharAtLineLength(StringBuilder output) {
		if(output.charAt(output.length()-1) == ' ') 
			return new StringBuilder(output.substring(0,output.length()-1));
		else
			return output;
	}
	
	private static String breakWordsBiggerThanLineLength(String word, int givenLength) {
		if (word.length()<= givenLength) {
			return word;
		}
		else {
			String aux = word.substring(0, givenLength-1);
			aux += "-\n";
			return aux + breakWordsBiggerThanLineLength(word.substring(givenLength-1), givenLength);
		}
	}
	
	private static String[] eraseIrregularWhitespaces(String text) {
		return text.trim().replaceAll(" +", " ").split(" ");
	}
}

