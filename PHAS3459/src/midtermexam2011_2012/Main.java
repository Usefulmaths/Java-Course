package midtermexam2011_2012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
		Set<Integer> zValues = positionsOfZValue(particles);
		for (int z : zValues) {
			System.out.println(1);
		}
	}

	public static Set<Integer> positionsOfZValue(final ArrayList<Particles> particles) {
		final HashMap<Integer, Integer> hashMapZValues = new HashMap<Integer, Integer>();
		for (final Particles p : particles) {
			hashMapZValues.put(p.getZ(), 0);
		}

		return hashMapZValues.keySet();
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
}
