package com.revature.data;

import com.revature.beans.Comment;
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
public class CommentHibernate implements CommentDAO{
    private HibernateUtil hu = HibernateUtil.getHibernateUtil();

    @Override
    public Comment add(Comment c) {
        Session s = hu.getSession();
        Transaction tx = null;
        try{
            tx = s.beginTransaction();
            s.save(c);
            tx.commit();
        }catch (Exception e){
            if (tx != null)
                tx.rollback();
        }finally{
            s.close();
        }
        return c;
    }

    @Override
    public Comment getById(Integer id) {
        Session s = hu.getSession();
        Comment c = s.get(Comment.class, id);
        s.close();
        return c;
    }

    @Override
    public Set<Comment> getAll() {
        Session s = hu.getSession();
        String query = "FROM Comment";
        Query<Comment> q = s.createQuery(query, Comment.class);
        List<Comment> commentList = q.getResultList();
        Set<Comment> commentSet = new HashSet<>();
        commentSet.addAll(commentList);
        s.close();
        return commentSet;
    }

    @Override
    public void update(Comment comment) {
        Session s = hu.getSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.update(comment);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
        } finally {
            s.close();
        }
    }

    @Override
    public void delete(Comment comment) {
        Session s = hu.getSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.delete(comment);
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
                s.createSQLQuery("SELECT setval('soup_salad_sandwich.user_comments_id_seq', max(id)) FROM user_comments").executeUpdate();
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
