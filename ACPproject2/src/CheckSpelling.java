import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;



public class CheckSpelling {
	static Main main = new Main();
	static WordLists wordLists = new WordLists();
	
	public static boolean spellCheck(String input, String[] dictionary)
	{
		String currentCheck = "";
		boolean noErrors = true;
		Scanner spellChecker = new Scanner(input);
		spellChecker.useDelimiter("\\s+");
		
		while(spellChecker.hasNext())
		{
			currentCheck = spellChecker.next();
			if(!isSpecial(currentCheck))
			{
				if(!checkWord(currentCheck, dictionary))
				{
					JOptionPane.showMessageDialog(null, currentCheck + " is spelled incorrectly");
					noErrors = false;
				}
			}
		}
		return noErrors;
	}
	
	public static boolean isSpecial(String input)
	{
		Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher match = pattern.matcher(input);
		return match.find();
	}
	
	public static boolean checkWord(String input, String[] dictionary)
	{
		boolean valid = false;
		int length = dictionary.length;
		int i = 0;
		while(!valid && i<length)
		{
			if(input.trim().equalsIgnoreCase(dictionary[i]))
			{
				valid = true;
			}
			i++;
		}
		return valid;
	}
	
public static void main(String str) throws IOException {
		String word;
		Scanner wordFile = new Scanner(new File("./src/Words.txt")).useDelimiter(",\\s*");
		List<String> tempList = new ArrayList<String>();
		while(wordFile.hasNext()) {
			tempList.add(wordFile.nextLine());
		}
		wordFile.close();
		
		String[] wordList = tempList.toArray(new String[0]);
		for (String s : wordList) {
		}
		String input = str;
		
		if(spellCheck(input, wordList))
		{
			JOptionPane.showMessageDialog(null, "There were no spelling errors");
		}
		else
		{
			
		}
	}
}
