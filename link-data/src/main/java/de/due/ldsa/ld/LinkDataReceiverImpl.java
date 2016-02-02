package de.due.ldsa.ld;

import java.util.ArrayList;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.due.ldsa.bd.DataProvider;
import de.due.ldsa.bd.DataSource;
import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.ld.exceptions.UndefinedFetchMethodException;
import de.due.ldsa.model.Comment;
import de.due.ldsa.model.Hashtag;
import de.due.ldsa.model.HumanProfile;
import de.due.ldsa.model.Profile;
import de.due.ldsa.model.ProfileFeed;
import de.due.ldsa.ld.services.CommentsService;
import de.due.ldsa.ld.services.HashtagsService;
import de.due.ldsa.ld.services.HumanProfilesService;
import de.due.ldsa.ld.services.LocationsService;
import de.due.ldsa.ld.services.MediaService;
import de.due.ldsa.ld.services.ProfileFeedsService;
import de.due.ldsa.ld.services.StreamsProviderService;
import de.due.ldsa.model.Location;
import de.due.ldsa.model.LocationImpl;
import de.due.ldsa.model.Media;

/**
 * @author Firas Sabbah
 *
 */
public class LinkDataReceiverImpl implements LinkDataReceiver {

	private static LinkDataReceiverImpl linkDataReceiverImpl;

	private HumanProfilesService humanProfilesService;
	private LocationsService locationsService;
	private ProfileFeedsService profileFeedsService;
	private HashtagsService hashtagsService;
	private CommentsService commentsService;
	private MediaService mediaService;

	private StreamsProviderService streamsProviderService;

	private Database databaseService;

	private boolean saveToDatabase;

	private DataSource dataSource;

	/**
	 * private constructor to prevent create instances
	 * 
	 */
	public LinkDataReceiverImpl() {
		humanProfilesService = HumanProfilesService.getInstance();
		locationsService = LocationsService.getInstance();
		profileFeedsService = ProfileFeedsService.getInstance();
		hashtagsService = HashtagsService.getInstance();
		commentsService = CommentsService.getInstance();
		mediaService = MediaService.getInstance();
		databaseService = DatabaseImpl.getInstance();
		streamsProviderService = StreamsProviderService.getInstance();
		saveToDatabase = false;
		dataSource = new DataProvider();
	}

	public static LinkDataReceiverImpl getInstance() {
		if (linkDataReceiverImpl == null) {
			linkDataReceiverImpl = new LinkDataReceiverImpl();
		}
		return linkDataReceiverImpl;
	}

	@Override
	public void setHumanProfiles(String humanProfilesJson) {
		List<HumanProfile> humanProfilesSteam = new Gson().fromJson(humanProfilesJson,
				new TypeToken<ArrayList<HumanProfile>>() {
				}.getType());

		streamsProviderService.setLastUpdatedHumanProfilesSteam(humanProfilesSteam);

		if (saveToDatabase) {
			for (HumanProfile humanProfile : humanProfilesSteam) {
				databaseService.saveHumanProfile(humanProfile);
			}

		}

		dataSource.setSourceData(humanProfilesSteam);
	}

	@Override
	public void setLocations(String locationsJson) {
		List<Location> locationsSteam = new Gson().fromJson(locationsJson, new TypeToken<ArrayList<LocationImpl>>() {
		}.getType());

		streamsProviderService.setLastUpdatedLocationsStream(locationsSteam);
		if (saveToDatabase) {
			for (Location location : locationsSteam) {
				databaseService.saveLocation((LocationImpl) location);
			}
		}

		dataSource.setSourceData(locationsSteam);
	}

	@Override
	public void setProfileFeeds(String profileFeedsJson) {
		List<ProfileFeed> profileFeedsStream = new Gson().fromJson(profileFeedsJson,
				new TypeToken<ArrayList<ProfileFeed>>() {
				}.getType());

		streamsProviderService.setLastUpdatedProfileFeedsStream(profileFeedsStream);
		if (saveToDatabase) {
			for (ProfileFeed profileFeed : profileFeedsStream) {
				databaseService.saveProfileFeed(profileFeed);
			}
		}

		dataSource.setSourceData(profileFeedsStream);
	}

	@Override
	public void setHashtags(String hashtagsJson) {
		List<Hashtag> hashtagsStream = new Gson().fromJson(hashtagsJson, new TypeToken<ArrayList<Hashtag>>() {
		}.getType());

		streamsProviderService.setLastUpdatedHashtagsStream(hashtagsStream);
		if (saveToDatabase) {
			for (Hashtag hashtag : hashtagsStream) {
				databaseService.saveHashtag(hashtag);
			}
		}

		dataSource.setSourceData(hashtagsStream);
	}

	@Override
	public void setComments(String commentsJson) {
		List<Comment> commentsStream = new Gson().fromJson(commentsJson, new TypeToken<ArrayList<Comment>>() {
		}.getType());

		streamsProviderService.setLastUpdatedCommentsStream(commentsStream);
		if (saveToDatabase) {
			for (Comment comment : commentsStream) {
				databaseService.saveComment(comment);
			}
		}

		dataSource.setSourceData(commentsStream);
	}

	@Override
	public void setMedia(String mediaJson) {
		List<Media> mediaStream = new Gson().fromJson(mediaJson, new TypeToken<ArrayList<Media>>() {
		}.getType());

		streamsProviderService.setLastUpdatedMediaSteam(mediaStream);
		if (saveToDatabase) {
			for (Media media : mediaStream) {
				databaseService.saveMedia(media);
			}
		}

		dataSource.setSourceData(mediaStream);

	}

	@Override
	public void setSaveToDatabase(boolean saveToDatabase) {
		this.saveToDatabase = saveToDatabase;
	}

	@Override
	public List<HumanProfile> getHumanProfiles(Fetch fetchMode) {
		List<HumanProfile> humanProfiles = null;
		try {
			humanProfiles = humanProfilesService.getHumanProfiles(fetchMode);
		} catch (UndefinedFetchMethodException e) {
			e.printStackTrace();
		}
		return humanProfiles;
	}

	@Override
	public List<Comment> getComments(Fetch fetchMode) {
		List<Comment> comments = null;
		try {
			comments = commentsService.getComments(fetchMode);
		} catch (UndefinedFetchMethodException e) {
			e.printStackTrace();
		}
		return comments;
	}

	@Override
	public List<Hashtag> getHashtags(Fetch fetchMode) {
		List<Hashtag> hashtags = null;
		try {
			hashtags = hashtagsService.getHashtags(fetchMode);
		} catch (UndefinedFetchMethodException e) {
			e.printStackTrace();
		}
		return hashtags;
	}

	@Override
	public List<Location> getLocations(Fetch fetchMode) {
		List<Location> locations = null;
		try {
			locations = locationsService.getLocations(fetchMode);
		} catch (UndefinedFetchMethodException e) {
			e.printStackTrace();
		}
		return locations;
	}

	@Override
	public List<ProfileFeed> getProfileFeeds(Fetch fetchMode) {
		List<ProfileFeed> profileFeeds = null;
		try {
			profileFeeds = profileFeedsService.getProfileFeeds(fetchMode);
		} catch (UndefinedFetchMethodException e) {
			e.printStackTrace();
		}
		return profileFeeds;
	}

	@Override
	public List<Media> getMedia(Fetch fetchMode) {
		List<Media> media = null;
		try {
			media = mediaService.getMedia(fetchMode);
		} catch (UndefinedFetchMethodException e) {
			e.printStackTrace();
		}
		return media;
	}

	@Override
	public void setData(String json) {
		// detect data type
		// redirect to the correct service.
	}

}
