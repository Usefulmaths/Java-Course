package midtermexam2011_2012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) {
		final String dataUrl = "http://www.hep.ucl.ac.uk/undergrad/3459/exam_data/2011-12/midterm/bpm.txt";
		BufferedReader dataInBufferedReader = null;
		ArrayList<Particles> particles = null;

		try {
			dataInBufferedReader = readInDataBR(dataUrl);
			particles = createArrayParticles(dataInBufferedReader);
			filterByZValues(particles);

		} catch (IOException e) {
			System.out.println("Error parsing in URL.");
		}
	}

	public static BufferedReader readInDataBR(final String urlString) throws IOException {
		final URL url = new URL(urlString);
		final InputStream stream = url.openStream();
		return new BufferedReader(new InputStreamReader(stream));
	}

	public static ArrayList<Particles> createArrayParticles(BufferedReader br) throws IOException {
		ArrayList<Particles> particles = new ArrayList<Particles>();

		String line;

		// Skips headers
		br.readLine();

		while ((line = br.readLine()) != null) {
			String[] particleDetails = line.split("	");

			final double x = Double.parseDouble(particleDetails[0]);
			final double y = Double.parseDouble(particleDetails[1]);
			final int z = Integer.parseInt(particleDetails[2]);

			Particles particle = new Particles(x, y, z);
			particles.add(particle);
		}
		return particles;
	}

	public static void filterByZValues(final ArrayList<Particles> particles) {
		final HashMap<Integer, Integer> hashMapZValues = new HashMap<Integer, Integer>();
		int numberOfBPMs;
		int separationDistance;
		double meanX;
		double meanY;

		ArrayList<Double> maximumRadialArray = new ArrayList<Double>();

		for (final Particles p : particles) {
			hashMapZValues.put(p.getZ(), 0);
		}

		Set<Integer> zValues = hashMapZValues.keySet();
		TreeSet<Integer> zValuesOrdered = new TreeSet<Integer>(zValues);

		numberOfBPMs = zValues.size();

		System.out.println("Number of BPMs: " + numberOfBPMs);
		System.out.println("Separation distance: " + 100 + "\n");

		for (int z : zValuesOrdered) {
			int numberOfParticles = numberOfBPMType(particles, z);
			meanX = meanOfX(particles, z);
			meanY = meanOfY(particles, z);

			System.out.println("Number of particles at BPMs " + z + ": " + numberOfParticles);
			System.out.println("Mean of X at BPMs " + z + ": " + meanX);
			System.out.println("Mean of Y at BPMs " + z + ": " + meanY);

			ArrayList<Double> radialArray = radialDistanceArrayEachBPM(particles, meanX, meanY, z);
			double rms = Math.sqrt(sumOfArraySquared(radialArray) / radialArray.size());

			Collections.sort(radialArray, Collections.reverseOrder());

			double maximumRadial = radialArray.get(0);

			maximumRadialArray.add(maximumRadial);

			System.out.println("RMS for BPS " + z + ": " + rms);
			System.out.println("Maximum radius at each BPM " + z + ": " + maximumRadial + "\n");
		}

		for (int i = 0; i < maximumRadialArray.size() - 1; i++) {

			double maxRadialDifferencesBetweenBPMs = maximumRadialArray.get(i) - maximumRadialArray.get(i + 1);

			double pipeSwitchRadius;

			if (maxRadialDifferencesBetweenBPMs > 2) {
				pipeSwitchRadius = maximumRadialArray.get(i + 1);

				for (double radius : maximumRadialArray) {
					
					if (radius > pipeSwitchRadius && radius < 2) {
						pipeSwitchRadius = radius;
					}
				}

				System.out.println("The pipe changes between " + zValuesOrdered.toArray()[i] + " and "
						+ zValuesOrdered.toArray()[i + 1]);

				System.out.println("The minimum radius of the pipe is " + pipeSwitchRadius);
			}
		}
	}

	public static int numberOfBPMType(final ArrayList<Particles> particles, final int BPMType) {
		int count = 0;
		for (final Particles p : particles) {
			if (p.getZ() == BPMType) {
				count++;
			}
		}
		return count;
	}

	public static double meanOfX(final ArrayList<Particles> particles, final int BPMType) {
		double sumOfX = 0;
		int numberOfXInBPM = numberOfBPMType(particles, BPMType);

		for (final Particles p : particles) {
			if (p.getZ() == BPMType) {
				sumOfX += p.getX();
			}
		}
		return sumOfX / numberOfXInBPM;
	}

	public static double meanOfY(final ArrayList<Particles> particles, final int BPMType) {
		double sumOfY = 0;
		int numberOfYInBPM = numberOfBPMType(particles, BPMType);

		for (final Particles p : particles) {
			if (p.getZ() == BPMType) {
				sumOfY += p.getY();
			}
		}
		return sumOfY / numberOfYInBPM;
	}

	public static double radialDistance(final Particles particle, double meanX, double meanY) {
		final double radialDistanceSquared = Math.pow(meanX - particle.getX(), 2)
				+ Math.pow(meanY - particle.getY(), 2);
		return Math.sqrt(radialDistanceSquared);
	}

	public static ArrayList<Double> radialDistanceArrayEachBPM(ArrayList<Particles> particles, double meanX,
			double meanY, int zValue) {
		ArrayList<Double> radialDistanceArray = new ArrayList<Double>();
		for (Particles p : particles) {
			if (p.getZ() == zValue) {
				radialDistanceArray.add(radialDistance(p, meanX, meanY));
			}
		}
		return radialDistanceArray;
	}

	public static double sumOfArraySquared(ArrayList<Double> radials) {
		double sumOfRadial = 0;
		for (double r : radials) {
			sumOfRadial += Math.pow(r, 2);
		}
		return sumOfRadial;
	}
}
