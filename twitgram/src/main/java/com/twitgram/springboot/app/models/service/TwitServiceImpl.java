package com.twitgram.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twitgram.springboot.app.models.dao.ITwitDao;
import com.twitgram.springboot.app.models.entity.Twit;

@Service
public class TwitServiceImpl implements ITwitService {
	
	@Autowired
	private ITwitDao twitDao;

	@Override
	@Transactional(readOnly = true)
	public List<Twit> findAll() {
		return (List<Twit>) twitDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Twit> obtenerTwitsOrdernadosFechaDesc(Pageable pageable) {
		return twitDao.obtenerTwitsOrdernadosFechaDesc(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Twit> obtenerTwitsPorNombreUsuario(String username, Pageable pageable) {
		return twitDao.obtenerTwitsPorNombreUsuario(username, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Long obtenerCantidadTwitsTotal() {
		return twitDao.obtenerCantidadTwitsTotal();
	}

	@Override
	@Transactional(readOnly = true)
	public int obtenerCantidadTwitsUsuario(Long id) {
		return twitDao.obtenerCantidadTwitsUsuario(id);
	}

	@Override
	@Transactional(readOnly = true)
	public long obtenerCantidadTwitsTotales() {
		return twitDao.obtenerCantidadTwitsTotales();
	}

}
