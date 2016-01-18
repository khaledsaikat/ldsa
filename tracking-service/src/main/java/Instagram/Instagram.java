package Instagram;
import java.io.IOException;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import scribe.model.OAuthConstants;
import scribe.model.OAuthRequest;
import scribe.model.Response;
import scribe.model.Token;
import scribe.model.Verb;
import scribe.utils.Preconditions;

/**
 * Instagram Service Class
 * <br> contains accessToken and connection settings
 * <br> provides methods to make actual requests to the Instagram endpoints
 * 
 * 
 * @author Vincent Nelius, Sachin Handiekar
 * @version 1.0
 */

public class Instagram {
	private Token accessToken;
	private final InstagramConfig config;
	private final InstagramService service;
	private Proxy requestProxy;
	
	public Instagram(Token accessToken, InstagramService service){
		Preconditions.checkNotNull(accessToken, "accessToken can't be null");
		this.accessToken = accessToken;
		this.service = service;
		config = new InstagramConfig();
	}
	
	public Response getUserInfo(String userId) throws IOException {
		Preconditions.checkEmptyString(userId, "UserId cannot be null or empty");
		String apiMethod = String.format(Methods.USERS_WITH_ID, userId);
		return getApiResponse(Verb.GET, apiMethod, null);
	}
	
	public Response getCurrentUserInfo() throws IOException {
		return getApiResponse(Verb.GET,Methods.USERS_SELF,null);
	}
	
	public Response getCurrentUserRecentMedia() throws IOException {
		return getApiResponse(Verb.GET, Methods.USERS_SELF_RECENT_MEDIA, null);
	}
	
	
	public Response getCurrentUserRecentMedia(int count, String minId, String maxId) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		if (maxId != null)
			params.put(InstagramQueryParam.MAX_ID, maxId);
		if (minId != null)
			params.put(InstagramQueryParam.MIN_ID, minId);
		if (count != 0)
			params.put(InstagramQueryParam.COUNT, String.valueOf(count));
		
		return getApiResponse(Verb.GET, Methods.USERS_SELF_RECENT_MEDIA, params);
	}
	
	
	public Response getRecentMediaFeed(String userId) throws IOException{
		Preconditions.checkEmptyString(userId, "UserId cannot be null or empty");
		String apiMethod = String.format(Methods.USERS_RECENT_MEDIA, userId);
		return getApiResponse(Verb.GET, apiMethod, null);
	}
	
	public Response getRecentMediaFeed(String userId, int count, String minId, String maxId) throws IOException {
		Preconditions.checkEmptyString(userId, "UserId cannot be null or empty");
		Map<String, String> params = new HashMap<String, String>();
		if (maxId != null)
			params.put(InstagramQueryParam.MAX_ID, maxId);
		if (minId != null)
			params.put(InstagramQueryParam.MIN_ID, minId);
		if (count != 0)
			params.put(InstagramQueryParam.COUNT, String.valueOf(count));
		
		String apiMethod = String.format(Methods.USERS_RECENT_MEDIA, userId);
		return getApiResponse(Verb.GET, apiMethod, params);
	}
	
	public Response getCurrentUserLikeMediaFeed(long maxLikeId, int count) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		if (maxLikeId > 0)
			params.put(InstagramQueryParam.MAX_LIKE_ID, String.valueOf(maxLikeId));
		if (count > 0)
			params.put(InstagramQueryParam.COUNT, String.valueOf(count));
		return getApiResponse(Verb.GET, Methods.USERS_SELF_LIKED_MEDIA, params);
	}
	
	public Response searchUser(String query, int count) throws IOException {
		Preconditions.checkNotNull(query, "search query cannot be null");
		Map<String, String> params = new HashMap<String, String>();
		params.put(InstagramQueryParam.SEARCH_QUERY, query);
		if (count > 0)
			params.put(InstagramQueryParam.COUNT, String.valueOf(count));
		return getApiResponse(Verb.GET, Methods.USERS_SEARCH, params);
	}
	
	public Response getObjectsCurrentUserFollows() throws IOException{
		return getApiResponse(Verb.GET, Methods.USERS_FOLLOWS, null);
	}
	
	public Response getObjectsCurrentUserFollowedBy() throws IOException {
		return getApiResponse(Verb.GET, Methods.USERS_FOLLOWED_BY, null);
	}
	
	public Response getCurrentUserFollowerRequests() throws IOException {
		return getApiResponse(Verb.GET, Methods.USERS_REQUESTED_BY, null);
	}
	
	public Response getCurrentUserRelationshipTo(String userId) throws IOException {
		Preconditions.checkEmptyString(userId, "userId cannot be null or empty");
		String apiMethod = String.format(Methods.USERS_ID_RELATIONSHIP, userId);
		return getApiResponse(Verb.GET, apiMethod, null);
	}
	
	public Response getMediaInfo(String mediaId) throws IOException {
		Preconditions.checkEmptyString(mediaId, "mediaId cannot be null or empty");
		String apiMethod = String.format(Methods.MEDIA_BY_ID, mediaId);
		return getApiResponse(Verb.GET, apiMethod, null);
	}
	
	public Response getMediaInfoByShortcode(String shortcode) throws IOException {
		Preconditions.checkEmptyString(shortcode, "shortcode cannot be null or empty");
		String apiMethod = String.format(Methods.MEDIA_BY_SHORTCODE, shortcode);
		return getApiResponse(Verb.GET, apiMethod, null);
	}
	
	public Response searchMediaByLocation(double latitude, double longitude, int distance) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put(InstagramQueryParam.LATITUDE, Double.toString(latitude));
		params.put(InstagramQueryParam.LONGITUDE, Double.toString(longitude));
		params.put(InstagramQueryParam.DISTANCE, String.valueOf(distance));
		
		return getApiResponse(Verb.GET, Methods.MEDIA_SEARCH, params);
	}
	
	public Response getMediaComments(String mediaId) throws IOException {
		String apiMethod = String.format(Methods.MEDIA_COMMENTS, mediaId);
		return getApiResponse(Verb.GET, apiMethod, null);
	}
	
	public Response getUsersLikingMedia(String mediaId) throws IOException {
		String apiMethod = String.format(Methods.LIKES_BY_MEDIA_ID, mediaId);
		return getApiResponse(Verb.GET, apiMethod, null);
	}
	
	public Response getTagInfo(String tagName) throws IOException {
		String apiMethod = String.format(Methods.TAGS_BY_NAME, tagName);
		return getApiResponse(Verb.GET, apiMethod, null);
	}
	
	public Response getRecentMediaByTag(String tagName, Long count, String minTagId, String maxTagId) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		if (count != null && count > 0)
			params.put(InstagramQueryParam.COUNT, String.valueOf(count));
		if (!StringUtils.isEmpty(minTagId))
			params.put(InstagramQueryParam.MIN_TAG_ID, minTagId);
		if (!StringUtils.isEmpty(maxTagId))
			params.put(InstagramQueryParam.MAX_TAG_ID, maxTagId);
		String apiMethod = String.format(Methods.TAGS_RECENT_MEDIA, tagName); 
		return getApiResponse(Verb.GET, apiMethod, params);
	}
	
	public Response searchTags(String tagName) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put(InstagramQueryParam.SEARCH_QUERY, tagName);
		return getApiResponse(Verb.GET, Methods.TAGS_SEARCH, params);
	}
	
	public Response getLocationInfo(String locationId) throws IOException {
		String apiMethod = String.format(Methods.LOCATIONS_BY_ID, locationId);
		return getApiResponse(Verb.GET, apiMethod, null);
	}
	
	public Response getRecentMediaByLocation(String locationId, String minId, String maxId) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		if (minId != null)
			params.put(InstagramQueryParam.MIN_ID, minId);
		if (maxId != null)
			params.put(InstagramQueryParam.MAX_ID, maxId);
		String apiMethod = String.format(Methods.LOCATIONS_RECENT_MEDIA_BY_ID, locationId);
		return getApiResponse(Verb.GET, apiMethod, params);
	}
	
	public Response searchLocation(double latitude, double longitude, Integer distance) throws IOException{
		Map<String, String> params = new HashMap<String, String>();
		if (distance != null && distance > 0)
			params.put(InstagramQueryParam.DISTANCE, Integer.toString(distance));
		params.put(InstagramQueryParam.LATITUDE, Double.toString(latitude));
		params.put(InstagramQueryParam.LONGITUDE, Double.toString(longitude));
		return getApiResponse(Verb.GET, Methods.LOCATIONS_SEARCH, params);
	}

	public Response searchFoursquareVenue(String foursquareId) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put(InstagramQueryParam.FOURSQUARE_V2_ID, foursquareId);
		return getApiResponse(Verb.GET, Methods.LOCATIONS_SEARCH, params);
	}
	
	public Response searchFacebookPlace(String facebookPlacesId) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put(InstagramQueryParam.FACEBOOK_PLACES_ID, facebookPlacesId);
		return getApiResponse(Verb.GET, Methods.LOCATIONS_SEARCH, params);
	}
	
	//Muss getestet werden
	public Response getNextPage(String nextPageURL) throws IOException {
		return getApiResponse(Verb.GET, nextPageURL, null);
	}
	
	protected Response getApiResponse(Verb verb, String methodName, Map<String, String> params) throws IOException {
		Response response;
		String apiResourceUrl = config.getApiURL() + methodName;
		OAuthRequest request = new OAuthRequest(verb, apiResourceUrl, this.service);
		configureConnectionSettings(request, config);
		//set Request Proxy
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				if (verb == Verb.GET){
					request.addQuerystringParameter(entry.getKey(), entry.getValue());
				} else {
					request.addBodyParameter(entry.getKey(), entry.getValue());
				}
			}
		}
		
		if (verb == Verb.GET || verb == Verb.DELETE){
			request.addQuerystringParameter(OAuthConstants.ACCESS_TOKEN, accessToken.getToken());
		} else {
			request.addBodyParameter(OAuthConstants.ACCESS_TOKEN, accessToken.getToken());
		}
		
		//Enforce Signed Requests implementieren
		
		response = request.send();
		
		return response;
	}
	
	public static void configureConnectionSettings(final OAuthRequest request, final InstagramConfig config){
		request.setConnectionKeepAlive(config.isConnectionKeepAlive());
	}
	
	public Token getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(Token accessToken) {
		this.accessToken = accessToken;
	}

	public Proxy getRequestProxy() {
		return requestProxy;
	}

	public void setRequestProxy(Proxy requestProxy) {
		this.requestProxy = requestProxy;
	}

	public InstagramConfig getConfig() {
		return config;
	}
}
