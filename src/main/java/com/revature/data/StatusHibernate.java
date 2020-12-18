package com.revature.data;

import com.revature.beans.Status;
import com.revature.utils.HibernateUtil;

import java.util.Set;

public class StatusHibernate implements StatusDAO{
    private HibernateUtil hu = HibernateUtil.getHibernateUtil();

    @Override
    public Status getById(Integer id) {
        Session s = hu.getSession();
        Status status = s.get(Status.class, id);
        s.close();
        return status;
    }

    @Override
    public Set<Status> getAll() {
        Session s = hu.getSession();
        String query = "FROM Status";
        Query<Status> q = s.createQuery(query, Status.class);
        List<Status> statusList = q.getResultList();
        Set<Status> statusSet = new HashSet<>();
        statusSet.addAll(statusList);
        s.close();
        return statusSet;
    }

    @Override
    public void update(Status status) {
        Session s = hu.getSession();
        Transaction tx = null;
        try{
            tx = s.beginTransaction();
            s.update(status);
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
    public void delete(Status status) {
        Session s = hu.getSession();
        Transaction tx = null;
        try{
            tx = s.beginTransaction();
            s.delete(status);
            tx.commit;
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
    public Status add(Status status) {
        Session s = hu.getSession();
        Transaction tx = null;
        try{
            tx = s.beginTransaction();
            s.save(status);
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
}
