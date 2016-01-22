/**
 * 
 */
package de.due.ldsa.ld.services;

import java.util.List;

import de.due.ldsa.model.Comment;
import de.due.ldsa.model.Hashtag;
import de.due.ldsa.model.HumanProfile;
import de.due.ldsa.model.Location;
import de.due.ldsa.model.Media;
import de.due.ldsa.model.ProfileFeed;

/**
 * @author Firas Sabbah
 *
 */
public class StreamsProviderService {
	private List<HumanProfile> lastUpdatedHumanProfilesSteam;
	private List<Location> lastUpdatedLocationsStream;
	private List<ProfileFeed> lastUpdatedProfileFeedsStream;
	private List<Hashtag> lastUpdatedHashtagsStream;
	private List<Comment> lastUpdatedCommentsStream;
	private List<Media> lastUpdatedMediaSteam;

	private static StreamsProviderService instance;

	public static StreamsProviderService getInstance() {
		if (instance == null) {
			instance = new StreamsProviderService();
		}
		return instance;
	}

	public List<HumanProfile> getLastUpdatedHumanProfilesSteam() {
		return lastUpdatedHumanProfilesSteam;
	}

	public void setLastUpdatedHumanProfilesSteam(List<HumanProfile> lastUpdatedHumanProfilesSteam) {
		this.lastUpdatedHumanProfilesSteam = lastUpdatedHumanProfilesSteam;
	}

	public List<Location> getLastUpdatedLocationsStream() {
		return lastUpdatedLocationsStream;
	}

	public void setLastUpdatedLocationsStream(List<Location> lastUpdatedLocationsStream) {
		this.lastUpdatedLocationsStream = lastUpdatedLocationsStream;
	}

	public List<ProfileFeed> getLastUpdatedProfileFeedsStream() {
		return lastUpdatedProfileFeedsStream;
	}

	public void setLastUpdatedProfileFeedsStream(List<ProfileFeed> lastUpdatedProfileFeedsStream) {
		this.lastUpdatedProfileFeedsStream = lastUpdatedProfileFeedsStream;
	}

	public List<Hashtag> getLastUpdatedHashtagsStream() {
		return lastUpdatedHashtagsStream;
	}

	public void setLastUpdatedHashtagsStream(List<Hashtag> lastUpdatedHashtagsStream) {
		this.lastUpdatedHashtagsStream = lastUpdatedHashtagsStream;
	}

	public List<Comment> getLastUpdatedCommentsStream() {
		return lastUpdatedCommentsStream;
	}

	public void setLastUpdatedCommentsStream(List<Comment> lastUpdatedCommentsStream) {
		this.lastUpdatedCommentsStream = lastUpdatedCommentsStream;
	}

	public List<Media> getLastUpdatedMediaSteam() {
		return lastUpdatedMediaSteam;
	}

	public void setLastUpdatedMediaSteam(List<Media> lastUpdatedMediaSteam) {
		this.lastUpdatedMediaSteam = lastUpdatedMediaSteam;
	}

	public static void setInstance(StreamsProviderService instance) {
		StreamsProviderService.instance = instance;
	}
	
	
}
