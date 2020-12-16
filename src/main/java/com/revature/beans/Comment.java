package com.revature.beans;

import java.util.Date;
import java.util.Objects;

public class Comment {
    private Integer id;
    private Dish dish;
    private User user;
    private Date date;
    private Integer like;
    private String message;

    public Comment() {
        id = 0;
        dish = null;
        user = null;
        date = null;
        like= 0;
        message = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(dish, comment.dish) && Objects.equals(user, comment.user) && Objects.equals(date, comment.date) && Objects.equals(like, comment.like) && Objects.equals(message, comment.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dish, user, date, like, message);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", dish=" + dish +
                ", user=" + user +
                ", date=" + date +
                ", like=" + like +
                ", message='" + message + '\'' +
                '}';
    }
}
