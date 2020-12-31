package com.revature.data;

import java.io.Serializable;
import java.util.Set;

import org.hibernate.*;
import org.springframework.stereotype.Repository;

import com.revature.beans.Image;
import com.revature.beans.User;
import com.revature.exception.AlreadyVotedException;
import com.revature.exception.NonUniqueUsernameException;
import com.revature.utils.HibernateUtil;

@Repository
public class ImageHibernate implements ImageDAO{
	 private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Image add(Image t) throws NonUniqueUsernameException, AlreadyVotedException {
		Session s = hu.getSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.saveOrUpdate(t);
            tx.commit();
            return t;
        } catch (Exception e) {
        	if (tx != null)
                tx.rollback();
        	throw new RuntimeException(e);
        } finally {
            s.close();
        }
	}

	@Override
	public Image getById(Serializable id) {
		Session s = hu.getSession();
		Image user = s.get(Image.class, id);
        s.close();
        return user;
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

	@Override
	public Image findByUserId(Integer userId) {
		Session s = hu.getSession();
		org.hibernate.query.Query<Image> query = s.createQuery("select i from Image i where i.userId = :userId", Image.class);
		query.setParameter("userId", userId);
		return query.uniqueResult();
	}
	
	

}
