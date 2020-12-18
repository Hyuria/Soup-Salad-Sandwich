package com.revature.data;


import com.revature.beans.Like;

import com.revature.utils.HibernateUtil;
import org.hibernate.*;

import java.util.Set;

public class LikeHibernate implements LikeDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
    @Override
    public Like getById(Integer id) {
    	 Session s = hu.getSession();     
         Like l = s.get(Like.class, id);
         s.close();
         return l;
       
    }

    @Override
    public Set<Like> getAll() {
    	Session s = hu,getSession();
        String query = "from thoughts";
        Query<Like> q = s.createQuery(query, Like.class);
        List<Like> likeList = q.getResultList();
        Set<Like> likeSet = new HashSet<>();
        likeSet.addAll(likeList);
        s.close();
        return likeSet;
    }

    @Override
    public void update(Like like) {
    	Session s = hu.getSession();
    	Transaction tx = null;
    	try {
    			tx = s.beginTransaction();
    			s.update(like);
    			tx.commit();
    	}catch(Exception e) {
    		if (tx != null)
    			tx.rollback();
    	}finally {
    		s.close();
    	}

    }

    @Override
    public void delete(Like like) {
    	Session s = hu.getSession();
    	Transaction tx = null;
    	try {
    			tx = s.beginTransaction();
    			s.delete(like);
    			tx.commit();
    	}catch(Exception e){
    			if(tx != null)
    				tx.rollback();
    	}finally {
    		s.close();
    	}

    }

    @Override
    public Like add(Like l) {
    	Session s = hu.getSession();
        Transaction tx = null;
        try {
        		tx = s.beginTransaction();
        		s.save(l);
        		tx.commit();
        }catch (Exception e) {
        		if (tx != null)
        				tx.rollback();
        }finally {
        	s.close();
        }
        return l;
    }
}
