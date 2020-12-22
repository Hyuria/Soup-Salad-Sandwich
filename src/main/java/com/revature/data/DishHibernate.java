package com.revature.data;

import org.hibernate.*;
import org.hibernate.query.Query;

import com.revature.beans.Dish;
import com.revature.utils.HibernateUtil;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class DishHibernate implements DishDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

    @Override
    public Dish add(Dish d) {
        Session s = hu.getSession();
        Transaction tx = null;
        try {
        		tx = s.beginTransaction();
        		s.save(d);
        		tx.commit();
        }catch (Exception e) {
        		if (tx != null)
        				tx.rollback();
        }finally {
        	s.close();
        }
        return d;
    }

    @Override
    public Dish getById(Integer id) {
        Session s = hu.getSession();
        Dish d = s.get(Dish.class, id);
        s.close();
        return d;
    }

    @Override
    public Set<Dish> getAll() {
        Session s = hu.getSession();
        String query = "FROM Dish";
        Query<Dish> q = s.createQuery(query, Dish.class);
        List<Dish> dishList = q.getResultList();
        Set<Dish> dishSet = new HashSet<>();
        dishSet.addAll(dishList);
        s.close();
        return dishSet;
    }

	@Override
	public Set<Dish> getByCategory(String categoryName){
		Session s = hu.getSession();
		String query = "FROM Dish where category.category = :categoryName";
		Query<Dish> q = s.createQuery(query, Dish.class);
		q.setParameter("categoryName", categoryName);
		List<Dish> dishList = q.getResultList();
		Set<Dish> dishSet = new HashSet<>();
		dishSet.addAll(dishList);
		s.close();
		return dishSet;
	}

    @Override
    public void update(Dish dish) {
    	Session s = hu.getSession();
    	Transaction tx = null;
    	try {
    			tx = s.beginTransaction();
    			s.update(dish);
    			tx.commit();
    	}catch(Exception e) {
    		if (tx != null)
    			tx.rollback();
    	}finally {
    		s.close();
    	}

    }

    @Override
    public void delete(Dish dish) {
    	Session s = hu.getSession();
    	Transaction tx = null;
    	try {
    			tx = s.beginTransaction();
    			s.delete(dish);
    			tx.commit();
    	}catch(Exception e){
    			if(tx != null)
    				tx.rollback();
    	}finally {
    		s.close();
    	}
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
                s.createSQLQuery("SELECT setval('soup_salad_sandwich.dish_id_seq', max(id)) FROM dish").executeUpdate();
            }else{
                s.createSQLQuery("ALTER SEQUENCE soup_salad_sandwich.dish_id_seq RESTART WITH 1").executeUpdate();
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
