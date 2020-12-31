package com.revature.data;

import com.revature.beans.Category;
import com.revature.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class CategoryHibernate implements CategoryDAO{
    private HibernateUtil hu = HibernateUtil.getHibernateUtil();

    @Override
    public Category add(Category c) {
        Session s = hu.getSession();
        Transaction tx = null;
        try{
            tx = s.beginTransaction();
            s.save(c);
            tx.commit();
        }
        catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
        }
        finally {
            s.close();
        }
        return c;
    }

    @Override
    public Category getById(Serializable id) {
        Session s = hu.getSession();
        Category c = s.get(Category.class, id);
        s.close();
        return c;
    }

    @Override
    public Set<Category> getAll() {
        Session s = hu.getSession();
        String query = "FROM Category";
        Query<Category> q = s.createQuery(query, Category.class);
        List<Category> categoryList = q.getResultList();
        Set<Category> categorySet = new HashSet<>();
        categorySet.addAll(categoryList);
        s.close();
        return categorySet;
    }

    @Override
    public void update(Category category) {
        Session s = hu.getSession();
        Transaction tx = null;
        try{
            tx = s.beginTransaction();
            s.update(category);
            tx.commit();
        }
        catch(Exception e){
            if(tx != null){
                tx.rollback();
            }
        }
        finally{
            s.close();
        }
    }

    @Override
    public void delete(Category category) {
        Session s = hu.getSession();
        Transaction tx = null;
        try{
            tx = s.beginTransaction();
            s.delete(category);
            tx.commit();
        }
        catch(Exception e){
            if(tx != null){
                tx.rollback();
            }
        }
        finally{
            s.close();
            resetSequence();
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
                s.createSQLQuery("SELECT setval('soup_salad_sandwich.category_id_seq', max(id)) FROM category").executeUpdate();
            }else{
                s.createSQLQuery("ALTER SEQUENCE soup_salad_sandwich.category_id_seq RESTART WITH 1").executeUpdate();
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
