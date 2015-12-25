package apiConnection;

import static org.junit.Assert.*;

import org.junit.Test;

public class OAuth2InstaGramTest {

	@Test
	public void test() {
		OAuth2 oAuth2 = (OAuth2) new OAuth2Instagram();
		assertEquals("create Service please", oAuth2.getAuthURL());

	}

}
