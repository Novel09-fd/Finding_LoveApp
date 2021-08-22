package com.novel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class AccessController {

	@GetMapping("/testing")
	public String jwtPage() {
		return "JWT BERHASIL DI AKSES";
	}
	
}
