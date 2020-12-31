package com.revature.data;

import java.util.Set;

import org.hibernate.*;

import com.revature.beans.Image;
import com.revature.exception.AlreadyVotedException;
import com.revature.exception.NonUniqueUsernameException;
import com.revature.utils.HibernateUtil;

public class ImageHibernate implements ImageDAO{
	 private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Image add(Image t) throws NonUniqueUsernameException, AlreadyVotedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Image> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Image t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Image t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image findByName(Image i) {
		// TODO Auto-generated method stub
		return null;
	}

}
