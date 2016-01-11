import java.io.OutputStream;

import scribe.model.OAuthConfig;
import scribe.model.SignatureType;

public class InstagramConfig extends OAuthConfig{

	private String baseURI;
	private String version;
	private String apiURL;
    private boolean connectionKeepAlive = false;
	private boolean enforceSignedRequest = false;
	
	public InstagramConfig(final String key, final String secret){
		this(key, secret, null, null, null, null, null, null, null);
	}
	
	public InstagramConfig(OAuthConfig config){
		this(config.getApiKey(),config.getApiSecret(),config.getCallback(),config.getSignatureType(),
				config.getScope(),config.getDebugStream(), config.getConnectTimeout(),config.getReadTimeout(),
				config.getGrantType());
	}
	
	public InstagramConfig(final String key, final String secret, final String callback, final SignatureType type,
            final String scope, final OutputStream stream, final Integer connectTimeout, final Integer readTimeout,
            final String grantType){
		super(key, secret, callback, type, scope, stream, connectTimeout, readTimeout, grantType);
		baseURI = InstagramConstants.BASE_URI;
		version = InstagramConstants.VERSION;
		apiURL = InstagramConstants.API_URL;
	}

	public String getBaseURI() {
		return baseURI;
	}

	public void setBaseURI(String baseURI) {
		this.baseURI = baseURI;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getApiURL() {
		return apiURL;
	}

	public void setApiURL(String apiURL) {
		this.apiURL = apiURL;
	}

	public boolean isConnectionKeepAlive() {
		return connectionKeepAlive;
	}

	public void setConnectionKeepAlive(boolean connectionKeepAlive) {
		this.connectionKeepAlive = connectionKeepAlive;
	}

	public boolean isEnforceSignedRequest() {
		return enforceSignedRequest;
	}

	public void setEnforceSignedRequest(boolean enforceSignedRequest) {
		this.enforceSignedRequest = enforceSignedRequest;
	}
	
	

}
