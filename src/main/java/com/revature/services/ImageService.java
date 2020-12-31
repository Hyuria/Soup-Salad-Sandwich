package com.revature.services;


import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Image;
import com.revature.data.ImageDAO;
import com.revature.exception.AlreadyVotedException;
import com.revature.exception.NonUniqueUsernameException;

@Service
public class ImageService {
	
	private final ImageDAO imageDao;
	
	@Autowired
	public ImageService(ImageDAO imagedao) {
		super();
		this.imageDao = imagedao;
	}
	
	public Image saveImage(Image image) throws NonUniqueUsernameException, AlreadyVotedException {
		return imageDao.add(image);
	}

	public Image findById(Long id) {
		// TODO Auto-generated method stub
		return imageDao.getById(id);
	}

	public Image findByUserId(Integer userId) {
		return imageDao.findByUserId(userId);
	}
}
