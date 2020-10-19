package com.twitgram.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.twitgram.springboot.app.models.entity.Usuario;

public interface IUsuarioDao extends PagingAndSortingRepository<Usuario, Long>{
	
	//@Query("select u from Usuario u where u.username = ?1")
	public Usuario findByUsername(String username);
	
	public Usuario findByEmail(String email);
	
	@Query(value = "select count(sigue_a_id) from usuario_sigue_a where sigue_a_id = ?1", nativeQuery = true)
	public Long numeroUsuariosLeSiguen(Long usuarioId);
	
	@Query(value = "select count(usuario_id) from usuario_sigue_a where usuario_id = ?1", nativeQuery = true)
	public Long numeroUsuariosSiguiendo(Long usuarioId);
}
