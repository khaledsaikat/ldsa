package Instagram;
import scribe.model.OAuthConfig;
import scribe.model.Token;
import scribe.oauth.OAuth20ServiceImpl;

/**
 * 
 * extends the OAuth20Service with functionality to create Instagram instances 
 * 
 * @author Vincent Nelius
 * @version 1.0
 *
 */

public class InstagramService extends OAuth20ServiceImpl {

	public InstagramService(InstagramApi api, OAuthConfig config) {
		super(api, config);
	}

	public Instagram getInstagram(Token accessToken){
		return new Instagram(accessToken, this);
	}

}
