package Instagram;

/**
 * Contains Instagrams API URL used by InstagramConfig
 * 
 * @author Vincent Nelius
 * @version 1.0
 *
 */

public class InstagramConstants {
	public static final String BASE_URI = "https://api.instagram.com";
	public static final String VERSION = "v1";
	public static final String API_URL = String.format("%s/%s", BASE_URI, VERSION);
}
