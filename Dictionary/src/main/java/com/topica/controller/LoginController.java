package com.topica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.topica.model.User;
import com.topica.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String getFirstPage() {
		
		return "redirect:login";
	}
	
	@GetMapping("/login")
	public String getLogin(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("user") User user) {
		int result = userService.checkUser(user);
		if(result != -1) {
			if(result == 0) {
				return "redirect:admin/word-list";
			} else {
				return "redirect:home";
			}
		} else {
			return "login";
		}
	}
}
