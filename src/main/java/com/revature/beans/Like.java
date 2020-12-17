package com.revature.beans;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_comments_id")
    private Comment comment;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    private Integer thoughts;

    public Like() {
        id = 0;
        comment = null;
        user = null;
        thoughts = 0;
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
        return thoughts;
    }

    public void setLike(Integer like) {
        this.thoughts = like;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like1 = (Like) o;
        return Objects.equals(id, like1.id) && Objects.equals(comment, like1.comment) && Objects.equals(user, like1.user) && Objects.equals(thoughts, like1.thoughts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comment, user, thoughts);
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", comment=" + comment +
                ", user=" + user +
                ", like=" + thoughts +
                '}';
    }
}
