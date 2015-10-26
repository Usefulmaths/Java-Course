package module4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class WordCounter {

	public static void main(String[] args) throws IOException {

		try {
			BufferedReader br = brFromURL("http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_text.txt");
			int numberOfWords = countWordsInResource(br);

			System.out.println("Number of words in the URL: " + numberOfWords);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	private static BufferedReader brFromURL(String urlName) throws IOException {
		URL u = new URL(urlName);
		InputStream in = u.openStream();
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(isr);

		return new BufferedReader(isr);

	}

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
