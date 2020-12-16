package com.revature.beans;

import java.util.Objects;

public class Like {
    private Integer id;
    private Comment comment;
    private User user;
    private Integer like;

    public Like() {
        id = 0;
        comment = null;
        user = null;
        like = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like1 = (Like) o;
        return Objects.equals(id, like1.id) && Objects.equals(comment, like1.comment) && Objects.equals(user, like1.user) && Objects.equals(like, like1.like);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comment, user, like);
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", comment=" + comment +
                ", user=" + user +
                ", like=" + like +
                '}';
    }
}
