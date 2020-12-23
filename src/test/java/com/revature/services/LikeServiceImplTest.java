package com.revature.services;

import com.revature.beans.Like;
import com.revature.data.CommentHibernate;
import com.revature.data.LikeHibernate;
import com.revature.data.UserHibernate;
import org.junit.jupiter.api.*;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LikeServiceImplTest {
    private CommentHibernate commentHibernate = new CommentHibernate();
    private UserHibernate userHibernate = new UserHibernate();

    private LikeHibernate likeDao = new LikeHibernate();
    private LikeServiceImpl likeService = new LikeServiceImpl(likeDao);

    private Like like = new Like();

    @Test
    @Order(1)
    void addLike() {
        like.setLike(1);
        like.setComment(commentHibernate.getById(1));
        like.setUser(userHibernate.getById(1));
        Like retLike = likeService.addLike(like);
        like.setId(retLike.getId());
        assertEquals(like, retLike);
    }

    @Test
    @Order(2)
    void getLikeById() {
        assertEquals(like, likeService.getLikeById(like.getId()));
    }

    @Test
    @Order(3)
    void getAll() {
        assertTrue(likeService.getAll().size() > 0);
    }

    @Test
    @Order(4)
    void updateLike() {
        like.setLike(0);
        likeService.updateLike(like);
        assertEquals(like,likeService.getLikeById(like.getId()));
    }

    @Test
    @Order(5)
    void deleteLike() {
        likeService.deleteLike(like);
        assertTrue(likeService.getLikeById(like.getId()) == null);
    }
}