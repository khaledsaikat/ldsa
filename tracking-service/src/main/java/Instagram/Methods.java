package Instagram;

/**
 * contains all URLs of the Instagram endpoints
 * 
 * @author Vincent Nelius
 * @version 1.0
 *
 */

public final class Methods {

	/**
	 * Get a list of users who have liked this media.
	 *
	 * Required scope : basic, public_content
	 */
	public static final String LIKES_BY_MEDIA_ID = "/media/%s/likes";

	/**
	 * Get information about a location.
	 * Required scope: public_content
	 */
	public static final String LOCATIONS_BY_ID = "/locations/%s";

	/**
	 * Get a list of recent media objects from a given location.
	 * Required scope: public_content
	 */
	public static final String LOCATIONS_RECENT_MEDIA_BY_ID = "/locations/%s/media/recent";

	/**
	 * Search for a location by geographic coordinate.
	 * Required scope: public_content
	 */
	public static final String LOCATIONS_SEARCH = "/locations/search";

	/**
	 * Get information about a media object.
	 * Required scope: basic, public_content
	 */
	public static final String MEDIA_BY_ID = "/media/%s";

    /**
     * Get information about a media object.
     * Required scope: basic, public_content
     */
    public static final String MEDIA_BY_SHORTCODE = "/media/shortcode/%s";

	/**
	 * Get a full list of comments on a media.
	 *
	 * Required scope: basic, public_content
	 */
	public static final String MEDIA_COMMENTS = "/media/%s/comments";

	/**
	 * Search for media in a given area.
	 * Required scope: public_content
	 */
	public static final String MEDIA_SEARCH = "/media/search";


	/**
	 * Get information about a tag object.
	 * Required scope: public_content
	 */
	public static final String TAGS_BY_NAME = "/tags/%s";

	/**
	 * Get a list of recently tagged media. Note that this media is ordered by
	 * when the media was tagged with this tag, rather than the order it was
	 * posted. Use the max_tag_id and min_tag_id parameters in the pagination
	 * response to paginate through these objects.
	 * 
	 * Required scope: public_content
	 */
	public static final String TAGS_RECENT_MEDIA = "/tags/%s/media/recent";

	/**
	 * Search for tags by name - results are ordered first as an exact match,
	 * then by popularity.
	 * 
	 * Required scope: public_content
	 */
	public static final String TAGS_SEARCH = "/tags/search";

	/**
	 * Get the list of users this user is followed by.
	 *
	 * Required scope: follower_list
	 */
	public static final String USERS_FOLLOWED_BY = "/users/self/followed-by";

	/**
	 * Get the list of users this user follows.
	 *
	 * Required scope: follower_list
	 */
	public static final String USERS_FOLLOWS = "/users/self/follows";
	
	/**
	 * Get the list of users  who have requested this user's permission to follow.
	 *
	 * Required scope: follower_list
	 */
	public static final String USERS_REQUESTED_BY = "/users/self/requested-by";

	/**
	 * Get information about the current user's relationship
	 * (follow/following/etc) to another user.
	 *
	 * Required scope: follower_list
	 */
	public static final String USERS_ID_RELATIONSHIP = "/users/%s/relationship";

	/**
	 * Get the most recent media published by a user.
	 * Required scope: public_content
	 */
	public static final String USERS_RECENT_MEDIA = "/users/%s/media/recent";

	/**
	 * Search for a user by name.
	 * Required scope: public_content
	 */
	public static final String USERS_SEARCH = "/users/search";

	/**
	 * Get basic information about a user (self).
	 * Required scope: basic
	 */
	public static final String USERS_SELF = "/users/self";

    /**
     * Get the most recent media published by the owner of the access_token.
     * Required scope: basic
     */
    public static final String USERS_SELF_RECENT_MEDIA = "/users/self/media/recent";

	/**
	 * See the authenticated user's list of media they've liked. Note that this
	 * list is ordered by the order in which the user liked the media. Private
	 * media is returned as long as the authenticated user has permission to
	 * view that media. Liked media lists are only available for the currently
	 * authenticated user.
	 * Required scope: public_content
	 */
	public static final String USERS_SELF_LIKED_MEDIA = "/users/self/media/liked";

	/**
	 * List the users who have requested this user's permission to follow
	 *
	 * Required scope: relationships
	 */
	public static final String USERS_SELF_REQUESTED_BY = "/users/self/requested-by";

	/**
	 * Get basic information about a user.
	 * Required scope: public_content
	 */
	public static final String USERS_WITH_ID = "/users/%s";
}