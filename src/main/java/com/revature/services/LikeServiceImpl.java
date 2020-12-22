package com.revature.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Like;
import com.revature.data.LikeDAO;
import com.revature.data.LikeHibernate;

@Service
public class LikeServiceImpl implements LikeService {
	private LikeDAO likeDAO;
	
	@Autowired
	public LikeServiceImpl(LikeDAO l) {
		likeDAO = l;
	}

	@Override
	public Like addLike(Like l) {
		return likeDAO.add(l);
	}

	@Override
	public Like getLikeById(Integer id) {
		return likeDAO.getById(id);
	}

	@Override
	public Set<Like> getAll() {
		return likeDAO.getAll();
	}

	@Override
	public void updateLike(Like l) {
		likeDAO.update(l);
	}

	@Override
	public void deleteLike(Like l) {
		likeDAO.delete(l);
	}

}
