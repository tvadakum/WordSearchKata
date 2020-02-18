import java.io.*;
import java.util.*;

public class WordSearchKata {
	
	public static void main(String[] args) {
		System.out.print(wordsearch(args));
	}

	public static String wordsearch(String[] args) {
		//Parse file
		java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();    
		System.setOut(new java.io.PrintStream(out)); 
		String filename = "C:\\Users\\memem\\eclipse-workspace\\WordSearchKata\\src\\default.txt";
		List<String> wordsearch = new ArrayList<String>();
		String[] wordlist = null;
		if(args.length > 0) {filename = args[0];}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			if (line != null) {wordlist = line.split(",");}
			line = reader.readLine();
			while (line != null) {
				wordsearch.add(line.replaceAll(",",""));
				line = reader.readLine();
			}
			reader.close();
		}
		catch (Exception e) {
			System.err.format("Word search file incorrect or could not be opened: '%s'.", filename);
		    e.printStackTrace();
		    return null;
		}
		//prepare wordsearch to search
		char[][] lettermap = new char[wordsearch.size()][];
		for(int i = 0; i < wordsearch.size(); i++) {
			lettermap[i] = wordsearch.get(i).toCharArray();
		}
		//find and map a set of coords to each word's letters
		Map<String, List<charTuple>> findings = new HashMap<String, List<charTuple>>();
		for(int j = 0; j < wordlist.length; j++) {
			//find first letter match
			boolean wordFound = false;
			for (int i = 0; i < lettermap.length; i++) {
				for (int k = 0; k < lettermap[i].length; k++) {
					if(lettermap[i][k] == wordlist[j].charAt(0)) {
						List<charTuple> list = findWord(lettermap, wordlist[j], 0, i, k, new ArrayList<charTuple>());
						if (!(list == null)) {
							findings.put(wordlist[j], list);
							wordFound = true;
						}
					}
					if(wordFound == true) {
						break;
					}
				}
				if(wordFound == true) {
					break;
				}
			} 
			System.out.print(wordlist[j] + ": ");
			for (int d = 0; d < findings.get(wordlist[j]).size(); d++) {
				System.out.print("("+ findings.get(wordlist[j]).get(d).getY()+ "," + findings.get(wordlist[j]).get(d).getX() + ")");
				if(d != findings.get(wordlist[j]).size() - 1) {
					System.out.print(",");
				}
			}
			System.out.println();
		}
		return out.toString();
	}
	//find the rest of the word from a certain index
	public static List<charTuple> findWord(char[][] lettermap, String word, int index, int x, int y, List<charTuple> list){
		if (word.charAt(index) != lettermap[x][y]) {
			return null;
		}
		else if (index == word.length()-1) {
			return list;
		}
		else {
			list.add(new charTuple(word.charAt(index), x, y));
			for(int i = 0; i < 8; i++) {
				int xchange = -1;
				int ychange = -1;
				if(i == 0 || i == 6) {
					xchange = 0;
				}
				if(i == 2 || i == 4 || i == 7) {
					xchange = 1;
				}
				if(i == 3 || i == 4) {
					ychange = 0;
				}
				if(i == 5 || i == 6 || i == 7) {
					ychange = 1;
				}
				//if next letter matches next letter, continue and add chartuple to list. else, clear list and break
				for(int j = 1; j < word.length(); j++) {
					if((0 <= (x+(j*xchange)) && (x+(j*xchange)) < lettermap.length) && (0 <= y+(j*ychange) && (y+(j*ychange)) < lettermap[0].length)) {
						if(lettermap[x+(j*xchange)][y+(j*ychange)] == word.charAt(j)) {
							list.add(new charTuple(word.charAt(j), x+(j*xchange), y+(j*ychange)));
							if (j == word.length() - 1) {
								return list;
							}
						}
						else {
							list.clear();
							list.add(new charTuple(word.charAt(index), x, y));
							break;
						}
					}
				}
			}
		}
		return null;
	}
}
