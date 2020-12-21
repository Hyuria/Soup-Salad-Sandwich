package com.revature.services;

import java.util.Set;

import com.revature.beans.Like;

public interface LikeService {
	// create
	public Like addLike(Like l);

	// read
	public Like getLikeById(Integer id);
	public Set<Like> getAll();

	// update
	public void updateLike(Like l);

	// delete
	public void deleteLike(Like l);
}
