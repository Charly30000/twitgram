package com.twitgram.springboot.app.models.service;

import com.twitgram.springboot.app.models.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
	
	public void save(Usuario usuario);
	
	public Usuario findByEmail(String email);
	
	public Long numeroUsuariosLeSiguen(Long usuarioId);
	
	public Long numeroUsuariosSiguiendo(Long usuarioId);
}
