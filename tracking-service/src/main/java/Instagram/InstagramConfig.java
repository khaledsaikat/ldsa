package Instagram;

public class InstagramConfig {

	private String baseURI;
	private String version;
	private String apiURL;
    private boolean connectionKeepAlive = false;
	private boolean enforceSignedRequest = false;
	
	
	
	public InstagramConfig(){
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
