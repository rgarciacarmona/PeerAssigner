package peerassigner;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PeerAssigner {

	private static List<Peer> toReview;
	private static List<Peer> reviewers;
	// How many reviews per assignment
	private static int numReviews = 3;
	// Destination file
	private static BufferedWriter bw;
	private static List<String> assignments = new ArrayList<String>();


	private static Random rand = new Random();

	public static void assignReviews(Peer reviewer, int number) throws IOException {
		for (int i = 0; i < number; i++) {
			boolean assigned = false;
			while (!assigned) {
				int randomIndex = rand.nextInt(toReview.size());
				Peer tempPeer = toReview.get(randomIndex);
				// If this reviewer hasn't been assigned this peer before
				// and the peer is not the reviewer, assign it
				if (!reviewer.hasReview(tempPeer) && !reviewer.equals(tempPeer)) {
					// Add the assignment to the text file
					assignments.add(tempPeer.getName() + " " + tempPeer.getSurnames() + " is reviewed by "
							+ reviewer.getName() + " " + reviewer.getSurnames() + "\n");
					// Make the assignment
					reviewer.addReview(tempPeer);
					assigned = true;
					toReview.remove(randomIndex);
				}
			}
		}
	}

	public static void main(String[] args) {
		reviewers = CSVReader.readPeersFromCSV("Students.csv");
		try {
			toReview = new ArrayList<Peer>();
			for (int i = 0; i < numReviews; i++) {
				toReview.addAll(reviewers);
			}
			for (Peer reviewer : reviewers) {
				assignReviews(reviewer, numReviews);
			}
			// Produces the output text file
			Path pathToFile = Paths.get("Reviewers.txt");
			bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
			// Print the list of reviewers to the text file
			bw.write("BY REVIEWER\n");
			bw.write("===========\n\n");
			bw.write(reviewers.toString());
			// Sort the list of assignments and print it to the text file
			bw.write("\n\nBY REVIEWED\n");
			bw.write("===========\n\n");
			Collections.sort(assignments);
			bw.write(assignments.toString());
			bw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
