package es.urjc.code.daw.library.book;

import org.springframework.stereotype.Service;

@Service
public class LineBreaker {
	public static String breakText(String text, int lineLength) {
		if(text.length()<=lineLength) {
			return text;
		}
		else {
			String aux = text.trim().replaceAll(" +", " ");
			String[] lines = aux.split(" ");
			String output = "";
			int charCount = 0;
			for(int i=0;i<lines.length;i++) {
				charCount += lines[i].length();
				if(charCount>lineLength) {
					if(output.charAt(output.length()-1) == ' ') {
						output = output.substring(0,output.length()-1);
					}
					output += "\n";
					charCount=0;
				}
				output += lines[i];
				if(i<lines.length-1)
					output += " ";
			}
			return output;
		}
	}
}


