package Instagram;
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
	private final InstagramService service;
	private Proxy requestProxy;
	
	public Instagram(Token accessToken, InstagramService service){
		Preconditions.checkNotNull(accessToken, "accessToken can't be null");
		this.accessToken = accessToken;
		this.service = service;
		config = new InstagramConfig();
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
	
	public Response getUserInfo() throws IOException {
		return getApiResponse(Verb.GET,Methods.USERS_SELF,null);
	}
	
	protected Response getApiResponse(Verb verb, String methodName, Map<String, String> params) throws IOException {
		Response response;
		String apiResourceUrl = config.getApiURL() + methodName;
		OAuthRequest request = new OAuthRequest(verb, apiResourceUrl, this.service);
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
