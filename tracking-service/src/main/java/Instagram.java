import java.io.IOException;
import java.net.Proxy;
import java.util.Map;

import scribe.model.OAuthConstants;
import scribe.model.OAuthRequest;
import scribe.model.Response;
import scribe.model.Token;
import scribe.model.Verb;
import scribe.utils.Preconditions;

public class Instagram {
	private Token accessToken;
	private final InstagramConfig config;
	private Proxy requestProxy;
	
	public Instagram(Token accessToken, InstagramConfig config){
		Preconditions.checkNotNull(accessToken, "accessToken can't be null");
		Preconditions.checkNotNull(config, "config can't be null");
		if (config.isEnforceSignedRequest()){
			Preconditions.checkEmptyString(accessToken.getSecret(), "enforce signed requests need client secret");
		}
		this.accessToken = accessToken;
		this.config = config;
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
	
	public Response getUserInfo(String userId) throws IOException {
		Preconditions.checkEmptyString(userId, "UserId cannot be null or empty");
		String apiMethod = String.format(Methods.USERS_WITH_ID, userId);
		return getApiResponse(Verb.GET,apiMethod,null);
	}
	
	protected Response getApiResponse(Verb verb, String methodName, Map<String, String> params) throws IOException {
		Response response;
		String apiResourceUrl = config.getApiURL() + methodName;
		OAuthRequest request = new OAuthRequest(verb, apiResourceUrl, config);
		configureConnectionSettings(request, config);
		//set Request Proxy
		if (params != null) {
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
	
	
}
