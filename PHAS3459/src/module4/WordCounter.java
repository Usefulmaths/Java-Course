package module4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class WordCounter {

	public static void main(String[] args) throws IOException {

		try {
			BufferedReader br = brFromURL("http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_text.txt");
			int numberOfWords = countWordsInResource(br);

			System.out.println("Number of words in the URL: " + numberOfWords);
		} catch (MalformedURLException e) {
			System.out.println("Error while parsing in URL: " + e.getMessage());

		} catch (IOException e) {
			System.out.println("Error reading in resource: " + e.getMessage());
		}
	}

	// Creates a BufferedReader Object from parsing a URL string
	private static BufferedReader brFromURL(String urlName) throws IOException, MalformedURLException {

		URL u = new URL(urlName);
		InputStream in = u.openStream();
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(isr);

		return new BufferedReader(isr);

	}

	// Counts the number of words within the given URL data stored within a
	// BufferedReader
	private static int countWordsInResource(BufferedReader dataAsBR) throws IOException {

		BufferedReader br = dataAsBR;
		Scanner scanner = new Scanner(br);
		String line;
		int counter = 0;

		while (scanner.hasNext()) {
			scanner.next();
			counter++;
		}
		return counter;
	}
}
