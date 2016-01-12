import java.io.IOException;

import Instagram.Instagram;
import Instagram.InstagramApi;
import Instagram.InstagramService;
import scribe.builder.ServiceBuilder;
import scribe.model.Response;
import scribe.model.Token;
import scribe.oauth.OAuthService;

public class Model {
	private OAuthService oauthService;
	private InstagramService instagramService;
	private String authorizationURL;
	private Token accessToken;
	private Instagram instagram;
	
	public Model(){
		super();
	}
	
	public String createService(String clientId, String clientSecret, String callbackUrl, 
			String scope, Integer connectTimeout, Integer readTimeout ){
		authorizationURL = null;
		try {
		oauthService = new ServiceBuilder()
				.provider(InstagramApi.class)
				.apiKey(clientId)
				.apiSecret(clientSecret)
				.callback(callbackUrl)
				.scope(scope)
				.grantType("authorization_code")
				.connectTimeout(connectTimeout)
				.readTimeout(readTimeout)
				.build();

		instagramService = (InstagramService) oauthService;
		authorizationURL = instagramService.getAuthorizationUrl(null);
		} catch (Exception e){
			e.printStackTrace();
		}
		return authorizationURL;
	}
	
	public boolean createAccessToken(String accessToken){
		this.accessToken = new Token(accessToken,"");
		return true;
	}
	
	public boolean createInstagram(){
		instagram = new Instagram(this.accessToken, instagramService);
		return true;
	}
	
	public String requestUserData(){
		Response response;
		try {
			response = instagram.getUserInfo();
			return response.getBody();
		} catch (IOException e) {
			return null;
		}
	}
}
