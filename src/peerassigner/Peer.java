package peerassigner;

import java.util.ArrayList;
import java.util.List;

public class Peer {

	private String name;
	private String surnames;
	
	private List<Peer> reviews;
	
	public Peer(String name, String surnames) {
		this.name = name;
		this.surnames = surnames;
		this.reviews = new ArrayList<Peer>();
	}

	public void addReview(Peer peer) {
		if (!this.reviews.contains(peer) && !peer.equals(this))
			this.reviews.add(peer);
	}
	
	public void removeReview(Peer peer) {
		if (this.reviews.contains(peer))
			this.reviews.remove(peer);
	}
	
	public boolean hasReview(Peer peer) {
		return this.reviews.contains(peer);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public List<Peer> getReviews() {
		return reviews;
	}

	public void setReviews(List<Peer> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		String data = name + " " + surnames + "\n";
		if (!reviews.isEmpty()) {
			for (Peer peer : reviews) {
				data = data + "\t Reviews " + peer.name + " " + peer.surnames + "\n";
			}
		}
		return data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surnames == null) ? 0 : surnames.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Peer other = (Peer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surnames == null) {
			if (other.surnames != null)
				return false;
		} else if (!surnames.equals(other.surnames))
			return false;
		return true;
	}
}
