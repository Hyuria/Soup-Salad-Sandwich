package src.main.java.com.revature.data;

import org.hibernate.*;

import com.revature.beans.Dish;
import com.revature.utils.HibernateUtil;

import java.util.Set;

public class DishHibernate implements DishDAO{
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
        Session s = hu,getSession();
        String query = "from dish";
        Query<Dish> q = s.createQuery(query, Dish.class);
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
}
