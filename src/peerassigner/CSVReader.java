package peerassigner;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

	public static List<Peer> readPeersFromCSV(String fileName) {
		List<Peer> peers = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
			String line = br.readLine();
			while (line != null) {
				String[] attributes = line.split(";");
				Peer peer = createPeer(attributes);
				peers.add(peer);
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return peers;
	}

	private static Peer createPeer(String[] metadata) {
		String surnames = metadata[0];
		String name = metadata[1];
		return new Peer(name, surnames);
	}

}
