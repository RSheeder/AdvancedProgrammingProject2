import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class Spellcheck {

	//SpellChecker spellChecker = engine.spellChecker();
	//Dictionary dictionary = spellChecker.customDictionary();
	
	public static void getWords(String[] arg) {
	
		HashMap<Integer, String> h =
			new HashMap<Integer, String>();
	
	BufferedReader reader;
	try {
		reader = new BufferedReader(new FileReader("./src/Words.txt"));
		String line = reader.readLine();
		//ArrayList<String> lineList = new ArrayList<>();
		int hCount = 0;
		Path path = Paths.get("./src/Words.txt");
		long lineCount = Files.lines(path).count();
		System.out.println(lineCount);
		while (line != null) {
			//System.out.println(line);
			hCount++;
			//lineList.add(line);
			line = reader.readLine();
			
			//for(int i = 0; i < 61402; i++) {
				h.put(hCount, line);
			System.out.println(hCount + "  "+ line);
			//System.out.println(lineList);

			//}
			//System.out.println(h+"\n");
		}
		if(line == null) {
			line = reader.readLine();
			reader.close();
		}
		//reader.close();
	} catch (IOException e) {
		e.printStackTrace();
		
	}
	
	
	//h.put(1, line);
	//System.out.println("Word# " + h);
	
	

	}
}
