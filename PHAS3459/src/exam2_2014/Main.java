package exam2_2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

	private static final String URL_BASE = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2014-15/";
	private static final String URL_BASE2 = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2014-15/part3/";

	public static void main(String[] args) {

		final Mean mean = new Mean();
		final Range range = new Range();

		try {
			part1And2(mean, range);
			part3();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void part3() throws IOException {
		final BufferedReader tides2004Reader = readInDataFromURL(URL_BASE2 + "tides-2004.txt");
		final BufferedReader tides2005Reader = readInDataFromURL(URL_BASE2 + "tides-2005.txt");
		final BufferedReader tides2006Reader = readInDataFromURL(URL_BASE2 + "tides-2006.txt");

		final BufferedReader sitesReaderPart3 = readInDataFromURL(URL_BASE2 + "sites.txt");

		final List<Tide> totalTidesPart3 = new ArrayList<>();
		final List<Tide> tides2004 = createListOfTidesFromReaderPart3(tides2004Reader);
		final List<Tide> tides2005 = createListOfTidesFromReaderPart3(tides2005Reader);
		final List<Tide> tides2006 = createListOfTidesFromReaderPart3(tides2006Reader);

		totalTidesPart3.addAll(tides2004);
		totalTidesPart3.addAll(tides2005);
		totalTidesPart3.addAll(tides2006);

		final Map<String, String> sitesMapPart3 = createSitesMap(sitesReaderPart3);

		final double largestTidalSurgePart3 = largestTidalSurge(totalTidesPart3);
		printDetailsOfLargestTidalSurge(totalTidesPart3, sitesMapPart3, largestTidalSurgePart3);
	}

	private static void part1And2(final Mean mean, final Range range) throws IOException {

		// Could have class to store these as list and have methods to create
		// Lists of tides from data. This way wouldn't require two redundant
		// methods and would tidy up the code quite a bit.
		
		final BufferedReader tides1999Reader = readInDataFromURL(URL_BASE + "tides-1999.txt");
		final BufferedReader tides2000Reader = readInDataFromURL(URL_BASE + "tides-2000.txt");
		final BufferedReader tides2001Reader = readInDataFromURL(URL_BASE + "tides-2001.txt");

		final BufferedReader sitesReader = readInDataFromURL(URL_BASE + "sites.txt");

		final List<Tide> totalTides = new ArrayList<>();
		final List<Tide> tides1999 = createListOfTidesFromReader(tides1999Reader);
		final List<Tide> tides2000 = createListOfTidesFromReader(tides2000Reader);
		final List<Tide> tides2001 = createListOfTidesFromReader(tides2001Reader);

		totalTides.addAll(tides1999);
		totalTides.addAll(tides2000);
		totalTides.addAll(tides2001);

		final Map<String, String> sitesMap = createSitesMap(sitesReader);

		final double highestObservedSeaLevel = highestObservedSeaLevel(totalTides);
		final List<Tide> tidesWithHighestObservedSeaLevel = retrieveTideBySeaLevel(totalTides, highestObservedSeaLevel);

		printDetailsOfTidesWithHighestActualSeaLevel(tidesWithHighestObservedSeaLevel, sitesMap);

		printStatisticsOfEachSite(totalTides, sitesMap, mean, range);

		final double largestTidalSurge = largestTidalSurge(totalTides);
		printDetailsOfLargestTidalSurge(totalTides, sitesMap, largestTidalSurge);
	}

	private static void printDetailsOfLargestTidalSurge(final List<Tide> tides, final Map<String, String> siteMap,
			final double tidalSurge) {
		System.out.println("\nDetails of largest tidal surge:\n");
		tides
		.stream()
		.filter(tide -> tide.getTidalSurge() == tidalSurge)
		.forEach(tide -> {
			System.out.println("\tTidal Surge Size: " + tide.getTidalSurge() + " m");
			System.out.println("\tSite: " + siteMap.get(tide.getIdentifier()));
			System.out.printf("\tDate: %d / %d / %d \n", tide.getDate().getDay(), tide.getDate().getMonth(),
					tide.getDate().getYear());
			System.out.printf("\tTime: %d:%d", tide.getDate().getTime().getHour(),
					tide.getDate().getTime().getMinutes());
			System.out.println();
		});
	}

	private static double largestTidalSurge(final List<Tide> tides) {
		return tides
				.stream()
				.mapToDouble(tide -> tide.getTidalSurge())
				.max()
				.getAsDouble();
	}

	private static void printStatisticsOfEachSite(final List<Tide> totalTides, Map<String, String> sitesMap,
			final Mean mean, final Range range) {
		System.out.println("Details of mean and range sea level at each site:\n");
		sitesMap
		.forEach((identifier, site) -> {
			List<Tide> tides = totalTides
					.stream()
					.filter(tide -> tide.getIdentifier().equals(identifier))
					.collect(Collectors.toList());

			final double meanOfList = mean.calculation(tides);
			final double rangeOfList = range.calculation(tides);

			System.out.println("Site: " + site);
			System.out.println("Mean sea level: " + meanOfList + " m");
			System.out.println("Range sea level: " + rangeOfList + " m");
			System.out.println();
		});
	}

	private static void printDetailsOfTidesWithHighestActualSeaLevel(final List<Tide> tides,
			final Map<String, String> siteMap) {
		System.out.println("Details of the highest observed tide(s): ");
		tides
		.forEach(tide -> {
			printDetailsOfTide(tide, siteMap);
		});
	}

	private static void printDetailsOfTide(final Tide tide, final Map<String, String> siteMap) {
		System.out.println("\tObserved Sea Level: " + tide.getSeaLevel().getActualSeaLevel() + " 	m");
		System.out.println("\tIdentifier: " + tide.getIdentifier());
		System.out.println("\tSite: " + siteMap.get(tide.getIdentifier()));
		System.out.printf("\tDate: %d / %d / %d \n", tide.getDate().getDay(), tide.getDate().getMonth(),
				tide.getDate().getYear());
		System.out.printf("\tTime: %d:%d", tide.getDate().getTime().getHour(), tide.getDate().getTime().getMinutes());
		System.out.println();
		System.out.println();
	}

	private static List<Tide> retrieveTideBySeaLevel(final List<Tide> tides, final double seaLevel) {
		return tides
				.stream()
				.filter(tide -> tide.getSeaLevel().getActualSeaLevel() == seaLevel)
				.collect(Collectors.toList());
	}

	private static double highestObservedSeaLevel(final List<Tide> tides) {
		return tides
				.stream()
				.mapToDouble(tide -> tide.getSeaLevel().getActualSeaLevel())
				.max()
				.getAsDouble();
	}

	private static BufferedReader readInDataFromURL(final String urlString) throws IOException {
		final URL url = new URL(urlString);
		final InputStream is = url.openStream();
		return new BufferedReader(new InputStreamReader(is));
	}

	private static List<Tide> createListOfTidesFromReader(final BufferedReader tideReader) {
		final List<Tide> tides = new ArrayList<>();

		tideReader.lines().forEach(line -> {
			final Scanner scanner = new Scanner(line);
			final String identifier = scanner.next();
			final Date date = new Date(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
					new Time(scanner.nextInt(), scanner.nextInt()));
			final SeaLevel seaLevel = new SeaLevel(scanner.nextDouble(), scanner.nextDouble());
			final Tide tide = new Tide(identifier, date, seaLevel);

			tides.add(tide);
		});
		return tides;
	}

	private static Map<String, String> createSitesMap(final BufferedReader sitesReader) throws IOException {
		final Map<String, String> sitesMap = new HashMap<>();

		sitesReader.readLine();

		sitesReader.lines().forEach(line -> {
			final Scanner scanner = new Scanner(line);

			final String site = scanner.next();
			final String port = scanner.next();

			sitesMap.put(port, site);
		});

		return sitesMap;
	}

	private static List<Tide> createListOfTidesFromReaderPart3(final BufferedReader tideReader) {
		final List<Tide> tides = new ArrayList<>();

		tideReader
		.lines()
		.forEach(line -> {
			final Scanner scanner = new Scanner(line);
			final Date date = new Date(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
					new Time(scanner.nextInt(), scanner.nextInt()));
			final String identifier = scanner.next();
			final SeaLevel seaLevel = new SeaLevel(scanner.nextDouble(), scanner.nextDouble());
			final Tide tide = new Tide(identifier, date, seaLevel);

			tides.add(tide);
		});
		return tides;
	}
}
