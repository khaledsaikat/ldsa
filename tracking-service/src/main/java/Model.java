import java.io.IOException;

import Instagram.Instagram;
import Instagram.InstagramApi;
import Instagram.InstagramService;
import de.due.ldsa.ld.LinkDataReceiver;
import de.due.ldsa.ld.LinkDataReceiverImpl;
import scribe.builder.ServiceBuilder;
import scribe.model.Response;
import scribe.model.Token;
import scribe.oauth.OAuthService;

/**
 * Contains InstagramService and provides methods 
 * to delegate Requests to the InstagramService
 * 
 * @author Vincent Nelius
 * @version 1.0
 */

public class Model {
	private OAuthService oauthService;
	private InstagramService instagramService;
	private String authorizationURL;
	private Token accessToken;
	private Instagram instagram;
	private LinkDataReceiver linkDataLayer;
	
	public Model(){
		super();
		linkDataLayer = new LinkDataReceiverImpl();
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
	
	public void createInstagram(){
		instagram = new Instagram(this.accessToken, instagramService);
	}
	
	public String requestBasicUserData(){
		Response response;
		try {
			response = instagram.getCurrentUserInfo();
			String responseText = response.getBody();
			linkDataLayer.setData(responseText);
			return responseText;
		} catch (IOException e) {
			return null;
		}
	}
}
