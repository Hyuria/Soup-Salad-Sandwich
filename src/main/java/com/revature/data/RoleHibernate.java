package com.revature.data;

import com.revature.beans.Role;
import com.revature.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleHibernate implements RoleDAO{
    private HibernateUtil hu = HibernateUtil.getHibernateUtil();

    @Override
    public Role add(Role r) {
        Session s = hu.getSession();
        Transaction tx = null;
        try{
            tx = s.beginTransaction();
            s.save(r);
            tx.commit();
        }catch (Exception e){
            if (tx != null)
                tx.rollback();
        }finally{
            s.close();
        }
        return r;
    }

    @Override
    public Role getById(Integer id) {
        Session s = hu.getSession();
        Role r = s.get(Role.class, id);
        s.close();
        return r;
    }

    @Override
    public Set<Role> getAll() {
        Session s = hu.getSession();
        String query = "FROM Role";
        Query<Role> q = s.createQuery(query, Role.class);
        List<Role> roleList = q.getResultList();
        Set<Role> roleSet = new HashSet<>();
        roleSet.addAll(roleList);
        s.close();
        return roleSet;
    }

    @Override
    public void update(Role role) {
        Session s = hu.getSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.update(role);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
        } finally {
            s.close();
        }
    }

    @Override
    public void delete(Role role) {
        Session s = hu.getSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.delete(role);
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
                s.createSQLQuery("SELECT setval('soup_salad_sandwich.roles_id_seq', max(id)) FROM roles").executeUpdate();
            }else{
                s.createSQLQuery("ALTER SEQUENCE soup_salad_sandwich.roles_id_seq RESTART WITH 1").executeUpdate();
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
