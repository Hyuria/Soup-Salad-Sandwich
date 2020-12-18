package com.revature.data;


import com.revature.beans.Like;

import com.revature.utils.HibernateUtil;
import org.hibernate.*;

import java.util.HashSet;
import java.util.List;
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
		Session s = hu.getSession();
		String query = "FROM Like";
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

	public void resetSequence(){
        /*
          Used for resetting the primary key 'id' to either 1 or the next highest number. Used primary in JUNIT tests.
          */
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			if (getAll().size() > 0){
				s.createSQLQuery("SELECT setval('soup_salad_sandwich.likes_id_seq', max(id)) FROM likes").executeUpdate();
			}else{
				s.createSQLQuery("ALTER SEQUENCE soup_salad_sandwich.likes_id_seq RESTART WITH 1").executeUpdate();
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}
}
