package com.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping({"/",
		"/main",
		"/login",
		"/about",
		"/analysis",
		"/database",
		"/request",
		"/request/users",
		"/request/relationships",
		"/request/media",
		"/request/comments",
		"/request/likes",
		"/request/tags",
		"/request/locations",
		"/token"})
	public String index(){
		return "forward:/index.html";
	}
}