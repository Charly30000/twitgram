package com.twitgram.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.twitgram.springboot.app.models.entity.Twit;

public interface ITwitDao extends PagingAndSortingRepository<Twit, Long>{
	
	/**
	 * NOTA IMPORTANTE:
	 * Todos los twits deben de obtenerse ordenados por la fecha en orden descendiente
	 * (del twit más nuevo al más antiguo)
	 */

	//@Query("select t from Twits t where usuario_id = ?1 order by create_at desc")
	//public List<Twit> fetchTwitsByUsuarioIdOrderByDesc(Long id);
	@Query("select t from Twit t join fetch t.usuario u order by t.createAt desc")
	public List<Twit> obtenerTwitsOrdernadosFechaDesc(Pageable pageable);
	
	/*@Query(value = "select t.id, t.create_at, t.texto, t.usuario_id from Twits t, Usuarios u "
			+ "where t.usuario_id = u.id and u.username = ?1 order by t.create_at desc", nativeQuery = true)*/
	@Query("select t from Twit t join fetch t.usuario u where u.username = ?1 order by t.createAt desc")
	public List<Twit> obtenerTwitsPorNombreUsuario(String username, Pageable pageable);
	
	@Query("select count(t) from Twit t")
	public Long obtenerCantidadTwitsTotal();
	
	@Query("select count(t) from Twit t where t.usuario.id = ?1")
	public int obtenerCantidadTwitsUsuario(Long id);
	
	@Query("select count(t) from Twit t")
	public long obtenerCantidadTwitsTotales();
	
}
