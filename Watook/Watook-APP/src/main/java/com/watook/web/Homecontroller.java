package com.watook.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Homecontroller {

	@GetMapping("/home")
	public String hello(Model model) {
		return "home";
	}
}
