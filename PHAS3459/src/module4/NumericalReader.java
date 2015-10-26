package module4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

public class NumericalReader {
	private int minValue;
	private int maxValue;
	private int nValues;
	private int sumOfValues;
	File file;
	FileWriter fw;

	public static void main(String[] args) throws IOException {
		NumericalReader nr = new NumericalReader();

		String dir = getOutputDirectory();
		System.out.println(dir);
		nr.analysisStart(dir);
		nr.analyseData("1 2 3 4 5");

	}

	private static String getOutputDirectory() {

		System.out.print("Enter directory location: ");
		Scanner scanner = new Scanner(System.in);
		String location = scanner.nextLine();

		return location;

	}

	private static BufferedReader brFromURL(String urlName) throws IOException {
		URL u = new URL(urlName);
		InputStream in = u.openStream();
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(isr);

		return new BufferedReader(isr);

	}

	void analysisStart(String dataFile) throws IOException {
		file = new File(dataFile);
		fw = new FileWriter(file);

		minValue = 0;
		maxValue = 0;
		nValues = 0;
		sumOfValues = 0;

	}

	void analyseData(String line) throws IOException {
		if (!line.isEmpty() || Character.isDigit(line.charAt(0))) {

			Scanner scanner = new Scanner(line);
			BufferedWriter bw = new BufferedWriter(fw);

			while (scanner.hasNext()) {
				String str = scanner.next();
				System.out.println(str);

				bw.write(str + "\n");

			}
			bw.close();
		}
	}

}
