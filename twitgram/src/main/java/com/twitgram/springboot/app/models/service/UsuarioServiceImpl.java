package com.twitgram.springboot.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twitgram.springboot.app.models.dao.IUsuarioDao;
import com.twitgram.springboot.app.models.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByEmail(String email) {
		return usuarioDao.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public Long numeroUsuariosLeSiguen(Long usuarioId) {
		return usuarioDao.numeroUsuariosLeSiguen(usuarioId);
	}

	@Override
	public Long numeroUsuariosSiguiendo(Long usuarioId) {
		return usuarioDao.numeroUsuariosSiguiendo(usuarioId);
	}

}
