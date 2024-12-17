package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HumanSystemController {

	@GetMapping("/")
	public String getIndex() {
		return "index";
	}
}
