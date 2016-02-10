package com.webapp.controllers.token;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TokenController {
	ArrayList<TokenDummy> list = new ArrayList<TokenDummy>();

	public TokenController() {
		TokenDummy a = new TokenDummy("http://placehold.it/300x300", "Bugs Bunny", "123123567");
		TokenDummy b = new TokenDummy("http://placehold.it/300x300", "Enton", "456787423");
		TokenDummy c = new TokenDummy("http://placehold.it/300x300", "That one Guy", "46788900");
		TokenDummy d = new TokenDummy("http://placehold.it/300x300", "Bugs Bunny - another one", "987654");

		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);

	}

	@RequestMapping("/api/getTokens")
	public @ResponseBody List<TokenDummy> getTokens() {

		return list;
	}
}
