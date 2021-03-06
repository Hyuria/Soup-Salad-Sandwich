package com.revature.beans;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Vote {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id")
    private Dish dish;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    public Vote() {
        id = 0;
        dish = null;
        user = null;
        category = null;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return Objects.equals(id, vote.id) && Objects.equals(dish, vote.dish) && Objects.equals(user, vote.user) && Objects.equals(category, vote.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dish, user, category);
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", dish=" + dish +
                ", user=" + user +
                ", category=" + category +
                '}';
    }
}
