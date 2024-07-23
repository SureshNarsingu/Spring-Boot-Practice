package com.rolebased.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ProductController {

	@PostMapping("/")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/new")
	public String showNewProductForm() {	
		return "new_product";
	}
}
