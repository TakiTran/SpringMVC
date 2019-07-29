package com.topica.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public String getLogin(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("token", null);
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("user") User user, Model model, HttpServletRequest req) {
		User u = userService.getUser(user);
		if (u == null || !u.getPassword().equals(user.getPassword())) {
			model.addAttribute("message", "Thông tin tài khoản sai.");
			return "login";
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("token", u.getRole());
			if(u.getRole().equals("admin")) {
				return "redirect:admin/word-list";
			} else {
				return "redirect:home";
			}
		}
	}
	
	@GetMapping("/page-403")
	public String get403Page() {
		return "403-page";
	}
}
