package com.revature.services;

import java.util.Set;

import com.revature.beans.Like;
import com.revature.data.LikeDAO;
import com.revature.data.LikeHibernate;

public class LikeServiceImpl implements LikeService {
	private LikeDAO likeDAO;
	
	public LikeServiceImpl() {
		likeDAO = new LikeHibernate();
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

	@Override
	public void resetSequence() {
		LikeHibernate lh = new LikeHibernate();
		lh.resetSequence();
	}

}
