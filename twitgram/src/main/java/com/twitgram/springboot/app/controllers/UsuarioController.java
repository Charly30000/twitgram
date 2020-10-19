package com.twitgram.springboot.app.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.twitgram.springboot.app.models.entity.Role;
import com.twitgram.springboot.app.models.entity.Twit;
import com.twitgram.springboot.app.models.entity.Usuario;
import com.twitgram.springboot.app.models.service.ITwitService;
import com.twitgram.springboot.app.models.service.IUsuarioService;

@Controller
@SessionAttributes("usuario")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ITwitService twitService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Secured({"ROLE_USER"})
	@GetMapping({"/", "home"})
	public String home(@RequestParam(name = "page", defaultValue = "1") int page, Model model, Authentication auth) {

		int twitsAMostrar = 5;
		if (page < 1) {
			page = 1;
		}
		
		Usuario usuario = usuarioService.findByUsername(auth.getName());
		
		int cantidadTwitsUsuario = twitService.obtenerCantidadTwitsUsuario(usuario.getId());
		int totalPages = (int) Math.ceil((cantidadTwitsUsuario + 0.0) / twitsAMostrar);

		if (totalPages < 1) {
			totalPages = 1;
		}
		if (page > totalPages) {
			page = totalPages;
		}
		page = page - 1;
		
		Pageable pageRequest = PageRequest.of(page, twitsAMostrar);
		List<Twit> twits = twitService.obtenerTwitsPorNombreUsuario(auth.getName(), pageRequest);
		
		Long numeroUsuariosLeSiguen = usuarioService.numeroUsuariosLeSiguen(usuario.getId());
		Long numeroUsuariosSiguiendo = usuarioService.numeroUsuariosSiguiendo(usuario.getId());
		
		if (hasRole("ROLE_ADMIN")) {
			model.addAttribute("info", "Tienes rango administrador");
		}
		
		page++;
		model.addAttribute("usuario", usuario);
		model.addAttribute("twits", twits);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("noActualPage", page);
		model.addAttribute("noTwits", cantidadTwitsUsuario);
		model.addAttribute("numeroUsuariosLeSiguen", numeroUsuariosLeSiguen);
		model.addAttribute("numeroUsuariosSiguiendo", numeroUsuariosSiguiendo);
		model.addAttribute("linkPage", "/home?page={page}");
		model.addAttribute("titulo", "Twitgram - Home");
		return "home";
	}

	@Secured({"ROLE_USER"})
	@GetMapping({ "/perfil" })
	public String home(Model model) {

		if (hasRole("ROLE_ADMIN")) {
			model.addAttribute("info", "Tienes rango administrador completo!");
		}
		return "perfil";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Twitgram - Crear cuenta");
		return "register";
	}
	
	@PostMapping("/register")
	public String crearUsuario(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Twitgram - Crear cuenta");
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(error -> {
				errores.put(error.getField(), String.format("El campo %s %s", error.getField(), error.getDefaultMessage()));
			});
			return "register";
		}
		
		if (existeUsuario(usuario)) {
			model.addAttribute("titulo", "Twitgram - Crear cuenta");
			model.addAttribute("error", "El nombre de usuario o el E-mail ya existe, "
					+ "por favor, prueba a cambiar uno de estos parametros");
			return "register";
		}
		
		List<Role> roles = new ArrayList<>();
		roles.add(new Role("ROLE_USER"));
		Usuario nuevoUsuario = new Usuario(usuario.getName(), usuario.getUsername(), 
				passwordEncoder.encode(usuario.getPassword()), usuario.getEmail(), new Date(), true, roles);

		usuarioService.save(nuevoUsuario);
		status.setComplete();
		return "/login";
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
	
	/**
	 * Comprueba si existe el usuario
	 * @param usuario
	 * @return boolean
	 */
	private boolean existeUsuario(Usuario usuario) {
		Usuario uComprobante = usuarioService.findByUsername(usuario.getUsername());
		
		if (uComprobante != null) {
			return true;
		}
		
		uComprobante = usuarioService.findByEmail(usuario.getEmail());
		
		if (uComprobante != null) {
			return true;
		}
		
		return false;
	}
}
