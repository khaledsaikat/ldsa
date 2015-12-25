package apiConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.jinstagram.auth.InstagramAuthService;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;
import org.jinstagram.auth.oauth.InstagramService;

/**
 * OAuth2 Class to communicate with Instagram OAuth2 Protocol
 * 
 * @author Salah Beck
 *
 */
public class OAuth2Instagram implements OAuth2 {
	private static final Token EMPTY_TOKEN = null;
	InstagramService service;

	public void createService(String clientID, String clientSecret, String callbackURL) {
		service = new InstagramAuthService().apiKey(clientID).apiSecret(clientSecret).callback(callbackURL)
				.scope("public_content").scope("follower_list").build();
	}

	public Token saveAccessToken(String clientID, String verifyString) {
		Token accessToken = null;
		accessToken = createAccessToken(verifyString);
		try {
			FileOutputStream fos = new FileOutputStream(verifyString + ".token");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(accessToken);

			fos.flush();
			oos.flush();
			fos.close();
			oos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return accessToken;
	}

	public Token getStoredAccessToken(String verifierString) {
		FileInputStream fis;
		Token token = null;
		File file = new File(verifierString + ".token");
		try {
			if (file.exists()) {
				fis = new FileInputStream(verifierString + ".token");
				ObjectInputStream ois = new ObjectInputStream(fis);
				token = (Token) ois.readObject();
				ois.close();
				fis.close();

			} else {
				System.err.println("no Token for given Client ID, proceeding to create one..");
				return null;
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}

	@Override
	public String getAuthURL() {
		String authorizationUrl = "create Service please";
		if (service != null) {
			authorizationUrl = service.getAuthorizationUrl(EMPTY_TOKEN);
			// TODO swap URL show to UI

		}

		return authorizationUrl;
	}

	@Override
	public Token createAccessToken(String verifyString) {
		Verifier verifier = new Verifier(verifyString);
		Token accessToken = null;
		accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);
		
		return accessToken;
	}

}
