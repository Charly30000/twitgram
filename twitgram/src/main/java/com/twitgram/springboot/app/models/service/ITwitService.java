package com.twitgram.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.twitgram.springboot.app.models.entity.Twit;

public interface ITwitService {

	public List<Twit> findAll();
	
	public List<Twit> obtenerTwitsOrdernadosFechaDesc(Pageable pageable);
	
	public List<Twit> obtenerTwitsPorNombreUsuario(String username, Pageable pageable);
	
	public Long obtenerCantidadTwitsTotal();
	
	public int obtenerCantidadTwitsUsuario(Long id);
	
	public long obtenerCantidadTwitsTotales();
}
