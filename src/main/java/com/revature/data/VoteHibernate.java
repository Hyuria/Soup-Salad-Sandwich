package com.revature.data;

import com.revature.beans.Vote;
import com.revature.exception.AlreadyVotedException;
import com.revature.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VoteHibernate implements VoteDAO{
    private final HibernateUtil hu = HibernateUtil.getHibernateUtil();

    @Override
    public Vote add(Vote v) throws AlreadyVotedException {
        Session s = hu.getSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.save(v);
            tx.commit();
        } catch (Exception e) {
            if (e.getMessage().contains("violates unique constraint")){
                throw new AlreadyVotedException();
            }
            if (tx != null)
                tx.rollback();
        } finally {
            s.close();
        }
        return v;
    }

    @Override
    public Vote getById(Integer id) {
        Session s = hu.getSession();
        Vote vote = s.get(Vote.class, id);
        s.close();
        return vote;
    }

    @Override
    public Set<Vote> getAll() {
        Session s = hu.getSession();
        String query = "FROM Vote";
        Query<Vote> q = s.createQuery(query, Vote.class);
        List<Vote> voteList = q.getResultList();
        Set<Vote> voteSet = new HashSet<>();
        voteSet.addAll(voteList);
        s.close();
        return voteSet;
    }

    @Override
    public void update(Vote vote) {
        Session s = hu.getSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.update(vote);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
        } finally {
            s.close();
        }
    }

    @Override
    public void delete(Vote vote) {
        Session s = hu.getSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.delete(vote);
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
                s.createSQLQuery("SELECT setval('soup_salad_sandwich.vote_id_seq', max(id)) FROM vote").executeUpdate();
            }else{
                s.createSQLQuery("ALTER SEQUENCE soup_salad_sandwich.vote_id_seq RESTART WITH 1").executeUpdate();
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
