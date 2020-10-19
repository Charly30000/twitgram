package com.twitgram.springboot.app.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
@SessionAttributes("usuario")
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			Model model, Principal principal, RedirectAttributes flash) {
		
		if (principal != null) {
			flash.addFlashAttribute("info", "Ya te encuentras logueado!");
			return "redirect:/home";
		}
		if (error != null) {
			model.addAttribute("error", "Usuario o contraseña inválidos!");
		}
		model.addAttribute("titulo", "Twitgram - Login");
		return "login";
	}
	
}
