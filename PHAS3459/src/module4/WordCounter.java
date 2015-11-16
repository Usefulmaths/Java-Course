package module4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class WordCounter {

	public static void main(String[] args) {
		final String url = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_text.txt";

		// Use try-with-resources for automatic close; no need to define BR
		// outside try.
		try (final BufferedReader br = brFromURL(url)) {
			final int numberOfWords = countWordsInResource(br);
			System.out.println("Number of words in the URL: " + numberOfWords);
		} catch (final MalformedURLException e) {
			System.out.println("Error while parsing in URL: " + e.getMessage());
		} catch (final IOException e) {
			System.out.println("Error reading in resource: " + e.getMessage());
		}
	}

	// Creates a BufferedReader Object from parsing a URL string
	private static BufferedReader brFromURL(final String urlName) throws MalformedURLException, IOException {
		final URL url = new URL(urlName);
		final InputStream stream = url.openStream();
		return new BufferedReader(new InputStreamReader(stream));
	}

	// Counts the number of words within the given URL data stored within a
	// BufferedReader
	private static int countWordsInResource(final BufferedReader dataAsBR) {
		final Scanner in = new Scanner(dataAsBR);
		int wordCount = 0;

		while (in.hasNext()) {
			in.next();
			wordCount++;
		}

		return wordCount;

		// return dataAsBR.lines()
		// .mapToInt(WordCounter::countWordsInLine)
		// .sum();
	}

	private static int countWordsInLine(final String line) {
		if (line.isEmpty()) {
			return 0;
		} else {
			return line.split(" ").length;
		}
	}
}
