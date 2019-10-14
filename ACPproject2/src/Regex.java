
public class Regex {

	public void CheckStrings() {
		
		String s = "abcd";
		if (s.matches("abcd")) {
			System.out.println("matches were found for: " + s);
		} else {
			System.out.println("No matches were found for: " + s);
		}
	}
	
	public void CheckStringsArray() {
		String[] sArray = {"a", "b", "c", "d"};
		
		for(int i=0; i<sArray.length; i++) {
			if(sArray[i].matches("c")) {
				System.out.println("Match was found for : " + sArray[i]);
			} else {
				System.out.println("No matches were found for: " + sArray[i]);
			}
		}
	}
	
	 public static void main(String[] args)
	  {
	    Regex sr = new Regex();
	    sr.CheckStrings();
	    sr.CheckStringsArray();
	      
	  }
}
