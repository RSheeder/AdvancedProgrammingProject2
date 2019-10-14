import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;



public class CheckSpelling {
	static Main main = new Main();
	
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
					System.out.println(currentCheck + " is spelled incorrectly");
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
	
public static void main(String arg[]) {
		
		//Dictionary
	
		String[] wordList = new String[12];
		wordList[0] = "greetings";
		wordList[1] = "hello";
		wordList[2] = "goodbye";
		

		String input = "greetings hello goodbye";
		
		if(spellCheck(input, wordList))
		{
			JOptionPane.showMessageDialog(null, "There were no errors");
			System.out.println("No Errors");
		}
		else
		{
			System.out.println("Errors");
		}
	}
}
