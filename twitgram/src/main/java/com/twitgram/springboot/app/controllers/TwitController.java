package com.twitgram.springboot.app.controllers;

import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.twitgram.springboot.app.models.entity.Twit;
import com.twitgram.springboot.app.models.service.ITwitService;

@Controller
@SessionAttributes("twit")
public class TwitController {
	
	@Autowired
	private ITwitService twitService;
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@GetMapping("/tendencias")
	public String tendencias(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
		
		if (page < 1) {
			page = 1;
		}
		int twitsAMostrar = 5;
		
		long cantidadTwits = twitService.obtenerCantidadTwitsTotales();
		
		int totalPages = (int) Math.ceil((cantidadTwits + 0.0) / twitsAMostrar);
		
		if (page > totalPages) {
			page = totalPages;
		}
		page = page - 1;
		Pageable pageRequest = PageRequest.of(page, 5);
		List<Twit> twits = twitService.obtenerTwitsOrdernadosFechaDesc(pageRequest);
		
		page++;
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("noActualPage", page);
		model.addAttribute("linkPage", "/tendencias?page={page}");
		model.addAttribute("titulo", "Twitgram - Tendencias");
		model.addAttribute("twits", twits);
		return "tendencias";
	}
	
	/**
	 * Retorna true si el usuario tiene el rol, false si no lo tiene.
	 * @param role
	 * @return boolean
	 */
	private boolean hasRole(String role) {

		SecurityContext context = SecurityContextHolder.getContext();

		if (context == null) {
			return false;
		}

		Authentication auth = context.getAuthentication();

		if (auth == null) {
			return false;
		}

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		return authorities.contains(new SimpleGrantedAuthority(role));
	}
}
