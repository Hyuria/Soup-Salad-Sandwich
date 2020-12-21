package com.revature.data;

import com.revature.beans.User;
import com.revature.exception.NonUniqueUsernameException;
import com.revature.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserHibernate implements UserDAO {
    private HibernateUtil hu = HibernateUtil.getHibernateUtil();

    @Override
    public User add(User u) throws NonUniqueUsernameException {
        Session s = hu.getSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.save(u);
            tx.commit();
        } catch (Exception e) {
            if (e.getMessage().contains("violates unique constraint")){
                throw new NonUniqueUsernameException();
            }
            if (tx != null)
                tx.rollback();
        } finally {
            s.close();
        }
        return u;
    }

    @Override
    public User getById(Integer id) {
        Session s = hu.getSession();
        User user = s.get(User.class, id);
        s.close();
        return user;
    }

    @Override
    public User getByUsername(String username){
      Session s = hu.getSession();
      String query = "FROM User WHERE User.username = :username";
      Query<User> q = s.createQuery(query, User.class);
      q.setParameter("username", username);
      User user = q.getSingleResult();
      s.close();
      return user;
   }

    @Override
    public Set<User> getAll() {
        Session s = hu.getSession();
        String query = "FROM User";
        Query<User> q = s.createQuery(query, User.class);
        List<User> userList = q.getResultList();
        Set<User> userSet = new HashSet<>();
        userSet.addAll(userList);
        s.close();
        return userSet;
    }

    @Override
    public void update(User user) {
        Session s = hu.getSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.update(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
        } finally {
            s.close();
        }
    }

    @Override
    public void delete(User user) {
        Session s = hu.getSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.delete(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
        } finally {
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
                s.createSQLQuery("SELECT setval('soup_salad_sandwich.user_comments_id_seq', max(id)) FROM users").executeUpdate();
            }else{
                s.createSQLQuery("ALTER SEQUENCE soup_salad_sandwich.user_comments_id_seq RESTART WITH 1").executeUpdate();
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
