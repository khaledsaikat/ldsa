package de.due.ldsa.ld;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import de.due.ldsa.bd.DataProvider;
import de.due.ldsa.bd.DataSource;
import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.ld.exceptions.UndefinedFetchMethodException;
import de.due.ldsa.ld.exceptions.UnexpectedJsonStringException;
import de.due.ldsa.ld.parsers.InstagramMediaMediaIdCommentsParser;
import de.due.ldsa.ld.services.CommentsService;
import de.due.ldsa.ld.services.HashtagsService;
import de.due.ldsa.ld.services.HumanProfilesService;
import de.due.ldsa.ld.services.LocationsService;
import de.due.ldsa.ld.services.MediaService;
import de.due.ldsa.ld.services.ProfileFeedsService;
import de.due.ldsa.ld.services.StreamsProviderService;
import de.due.ldsa.model.Comment;
import de.due.ldsa.model.Hashtag;
import de.due.ldsa.model.HumanProfile;
import de.due.ldsa.model.Location;
import de.due.ldsa.model.LocationImpl;
import de.due.ldsa.model.Media;
import de.due.ldsa.model.Profile;
import de.due.ldsa.model.ProfileFeed;
import de.due.ldsa.model.SocialNetworkContent;
import de.due.ldsa.model.SocialNetworkContentImpl;

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

	private boolean onlineAnalysis;

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
		onlineAnalysis = false;
		dataSource = DataProvider.getInstance();
	}

	public static LinkDataReceiverImpl getInstance() {
		if (linkDataReceiverImpl == null) {
			linkDataReceiverImpl = new LinkDataReceiverImpl();
		}
		return linkDataReceiverImpl;
	}

	@Override
	public void setHumanProfiles(String humanProfilesJson) throws UnexpectedJsonStringException {
		String jsonObject = getJsonData(humanProfilesJson);
		List<HumanProfile> humanProfilesSteam = new ArrayList<HumanProfile>();
		try {
			humanProfilesSteam = new Gson().fromJson(jsonObject, new TypeToken<ArrayList<HumanProfile>>() {
			}.getType());

		} catch (JsonParseException e) {
			try {
				HumanProfile humanProfile = new Gson().fromJson(jsonObject, HumanProfile.class);
				humanProfilesSteam.add(humanProfile);
			} catch (Exception ex) {
				throw new UnexpectedJsonStringException("Json string must be list of HumanProfiles");
			}

		}

		streamsProviderService.setLastUpdatedHumanProfilesSteam(humanProfilesSteam);

		if (onlineAnalysis) {
			for (HumanProfile humanProfile : humanProfilesSteam) {
				databaseService.saveHumanProfile(humanProfile);
			}

		}

		dataSource.setSourceData(humanProfilesSteam);
	}

	private String getJsonData(String humanProfilesJson) {
		String dataField = "";
		try {
			dataField = new JSONObject(humanProfilesJson).getString("data");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return dataField;
	}

	@Override
	public void setLocations(String locationsJson) throws UnexpectedJsonStringException {
		String jsonObject = getJsonData(locationsJson);

		List<Location> locationsSteam = new ArrayList<Location>();
		try {
			locationsSteam = new Gson().fromJson(jsonObject, new TypeToken<ArrayList<LocationImpl>>() {
			}.getType());
		} catch (JsonParseException e) {
			try {
				LocationImpl loc = new Gson().fromJson(jsonObject, LocationImpl.class);
				locationsSteam.add(loc);
			} catch (JsonParseException ex) {
				throw new UnexpectedJsonStringException("Json string must be list of Locations");
			}
		}

		streamsProviderService.setLastUpdatedLocationsStream(locationsSteam);
		if (onlineAnalysis) {
			for (Location location : locationsSteam) {
				databaseService.saveLocation((LocationImpl) location);
			}
		}

		dataSource.setSourceData(locationsSteam);
	}

	@Override
	public void setProfileFeeds(String profileFeedsJson) throws UnexpectedJsonStringException {
		String jsonObject = getJsonData(profileFeedsJson);
		List<ProfileFeed> profileFeedsStream = new ArrayList<ProfileFeed>();
		try {
			profileFeedsStream = new Gson().fromJson(jsonObject, new TypeToken<ArrayList<ProfileFeed>>() {
			}.getType());
		} catch (JsonParseException e) {
			try {
				ProfileFeed profileFeed = new Gson().fromJson(jsonObject, ProfileFeed.class);
				profileFeedsStream.add(profileFeed);
			} catch (Exception ex) {
				throw new UnexpectedJsonStringException("Json string must be list of profileFeeds");
			}
		}

		streamsProviderService.setLastUpdatedProfileFeedsStream(profileFeedsStream);
		if (onlineAnalysis) {
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
		if (onlineAnalysis) {
			for (Hashtag hashtag : hashtagsStream) {
				databaseService.saveHashtag(hashtag);
			}
		}

		dataSource.setSourceData(hashtagsStream);
	}

	@Override
	public void setComments(String commentsJson) throws UnexpectedJsonStringException {
		String jsonObject = getJsonData(commentsJson);
		List<Comment> commentsStream = new ArrayList<Comment>();
		List<Comment> arrComment = new ArrayList<>();
		List<Profile> arrProfile = new ArrayList<>();
		try {
			InstagramMediaMediaIdCommentsParser parser = InstagramMediaMediaIdCommentsParser.INSTANCE;
			JSONObject jsonObj = new JSONObject(commentsJson);
			List<SocialNetworkContent> arr = parser.parse(jsonObj);			
			for(SocialNetworkContent s : arr) {
				if(s.getClass().equals(Comment.class)) {
					arrComment.add((Comment) s);
				} else {
					arrProfile.add((Profile) s);
				}
			}
		} catch (JsonParseException | JSONException e) {
			try {
				Comment comment = new Gson().fromJson(jsonObject, Comment.class);
				commentsStream.add(comment);
			} catch (Exception ex) {
				throw new UnexpectedJsonStringException("Json string must be list of comments");
			}
		}
		
		for(Comment c : arrComment) {
			databaseService.saveComment(c);
		}
		for(Profile p : arrProfile) {
			databaseService.autoSaveProfile(p);
		}
		if(onlineAnalysis) {
			dataSource.setSourceData(arrComment);
		}		
	}

	@Override
	public void setMedia(String mediaJson) {
		List<Media> mediaStream = new Gson().fromJson(mediaJson, new TypeToken<ArrayList<Media>>() {
		}.getType());

		streamsProviderService.setLastUpdatedMediaSteam(mediaStream);
		if (onlineAnalysis) {
			for (Media media : mediaStream) {
				databaseService.saveMedia(media);
			}
		}

		dataSource.setSourceData(mediaStream);

	}

	@Override
	public void setOnlineAnalysis(boolean onlineAnalysis) {
		this.onlineAnalysis = onlineAnalysis;
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
	@Deprecated
	public void setData(String json) {
		try {
			JSONObject jsonObject = new JSONObject(json);
			Gson gson = new Gson();

			HumanProfile profile = gson.fromJson(jsonObject.getString("data"), HumanProfile.class);
			List<HumanProfile> humanProfilesStream = new ArrayList<HumanProfile>();
			humanProfilesStream.add(profile);
			dataSource.setSourceData(humanProfilesStream);

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		}

	}

}
