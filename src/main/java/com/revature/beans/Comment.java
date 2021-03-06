package com.revature.beans;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


@Entity
@Table (name = "user_comments")
public class Comment {
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="dish_id")
    private Dish dish;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
    @Column(name = "date_posted", insertable=false)
    private Date date;
    @Column(name = "like_count")
    private Integer like;
    private String message;


    public Comment() {
        id = 0;
        dish = null;
        user = null;
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
