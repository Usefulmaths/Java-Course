package module4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WordCounter {

	public static void main(String[] args) throws IOException {

		try {
			final BufferedReader br = brFromURL(
					"http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_text.txt");
			final int numberOfWords = countWordsInResource(br);

			System.out.println("Number of words in the URL: " + numberOfWords);
		} catch (final MalformedURLException e) {
			System.out.println("Error while parsing in URL: " + e.getMessage());

		} catch (final IOException e) {
			System.out.println("Error reading in resource: " + e.getMessage());
		}
	}

	// Creates a BufferedReader Object from parsing a URL string
	private static BufferedReader brFromURL(final String urlName) throws IOException, MalformedURLException {
		final URL url = new URL(urlName);
		final InputStream stream = url.openStream();
		return new BufferedReader(new InputStreamReader(stream));
	}

	// Counts the number of words within the given UjRL data stored within a
	// BufferedReader
	private static int countWordsInResource(final BufferedReader dataAsBR) throws IOException {		
		return dataAsBR.lines()
				.mapToInt(WordCounter::countWordsInLine)
				.sum();
	}

	private static int countWordsInLine(final String line) {
		if (line.isEmpty()) {
			return 0;
		} else {
			return line.split(" ").length;
		}
	}
}
