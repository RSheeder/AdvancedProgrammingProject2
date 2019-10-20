import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Spellcheck {

	//SpellChecker spellChecker = engine.spellChecker();
	//Dictionary dictionary = spellChecker.customDictionary();
	
	public static void main(String[] arg) {
	
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
		//System.out.println(lineCount);
		while (line != null) {
			hCount++;
			line = reader.readLine();
			h.put(hCount, line);
			
		}
		if(line == null) {
			line = reader.readLine();
			reader.close();
		}
	} catch (IOException e) {
		e.printStackTrace();
		
	}
	//h.put(1, line);
	//System.out.println("Word# " + h);
	}
}
