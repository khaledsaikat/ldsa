package Instagram;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import scribe.builder.api.DefaultApi20;
import scribe.exceptions.OAuthException;
import scribe.extractors.AccessTokenExtractor;
import scribe.model.OAuthConfig;
import scribe.model.Token;
import scribe.model.Verb;
import scribe.oauth.OAuthService;
import scribe.utils.Preconditions;

public class InstagramApi extends DefaultApi20 {

	@Override
	public String getAccessTokenEndpoint() {
		return InstagramOAuthConstants.ACCESS_TOKEN_ENDPOINT;
	}
	
	@Override
	public Verb getAccessTokenVerb(){
		return Verb.POST;
	}

	@Override
	public String getAuthorizationUrl(OAuthConfig config) {
		Preconditions.checkValidUrl(config.getCallback(),
				"Must provide a valid url as callback. Instagram does not support OOB");

		// Append scope if present
		if (config.hasScope()) {
			return String.format(InstagramOAuthConstants.SCOPED_AUTHORIZE_URL, config.getApiKey(),
					formURLEncode(config.getCallback()), formURLEncode(config.getScope()));
		} else {
			return String.format(InstagramOAuthConstants.AUTHORIZE_URL, config.getApiKey(), formURLEncode(config.getCallback()));
		}
	}
	
	@Override
	public AccessTokenExtractor getAccessTokenExtractor(){
		return new AccessTokenExtractor(){
			private Pattern accessTokenPattern = Pattern.compile(InstagramOAuthConstants.ACCESS_TOKEN_EXTRACTOR_REGEX);
			
			@Override
			public Token extract(String response) {
				Preconditions.checkEmptyString(response, "Cannot extract a token from a null or empty String");

				Matcher matcher = accessTokenPattern.matcher(response);

				if (matcher.find()) {
					return new Token(matcher.group(1), "", response);
				} else {
					throw new OAuthException("Cannot extract an acces token. Response was: " + response);
				}
			}
			
		};
	}
	
	@Override
	public OAuthService createService(OAuthConfig config){
		return new InstagramService(this, config);
	}
	
	public static String formURLEncode(String string) {
		Preconditions.checkNotNull(string, "Cannot encode null string");

		try {
			return URLEncoder.encode(string, "UTF-8");
		} catch (UnsupportedEncodingException uee) {
			throw new IllegalStateException("Cannot find specified encoding", uee);
		}
	}
	

}
