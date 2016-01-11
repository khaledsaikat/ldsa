import scribe.model.OAuthConfig;
import scribe.model.Token;
import scribe.oauth.OAuth20ServiceImpl;

public class InstagramService extends OAuth20ServiceImpl {

	public InstagramService(InstagramApi api, OAuthConfig config) {
		super(api, config);
		// TODO Auto-generated constructor stub
	}

	public Instagram getInstagram(Token accessToken){
		return new Instagram(accessToken, new InstagramConfig(this.getConfig()));
	}

}
