package com.revature.data;

import com.revature.beans.Dish;
import com.revature.beans.Vote;
import com.revature.exception.AlreadyVotedException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VoteHibernateTest {
    private CategoryHibernate categoryHibernate = new CategoryHibernate();
    private DishHibernate dishHibernate = new DishHibernate();
    private VoteHibernate voteHibernate = new VoteHibernate();
    private StatusHibernate statusHibernate = new StatusHibernate();
    private UserHibernate userHibernate = new UserHibernate();

    private Vote vote = new Vote();
    private Dish dish = new Dish();

    @Test
    @Order(1)
    void add() throws AlreadyVotedException {
        dish.setStatus(statusHibernate.getById(1));
        dish.setCategory(categoryHibernate.getById(1));
        dish.setName("temp");
        dish.setPhoto_url("temp.temp");

        vote.setCategory(categoryHibernate.getById(1));
        vote.setDish(dishHibernate.add(dish));
        vote.setUser(userHibernate.getById(1));
        Vote retVote = voteHibernate.add(vote);
        vote.setId(retVote.getId());
        assertEquals(vote, retVote);
    }

    @Test
    @Order(2)
    void getById() {
        assertEquals(vote, voteHibernate.getById(vote.getId()));
    }

    @Test
    @Order(3)
    void getAll() {
        assertTrue(voteHibernate.getAll().size() > 0);
    }

    @Test
    @Order(4)
    void update() {
        vote.setUser(userHibernate.getById(2));
        voteHibernate.update(vote);
        assertEquals(vote, voteHibernate.getById(vote.getId()));
    }

    @Test
    @Order(5)
    void delete() {
        voteHibernate.delete(vote);
        dishHibernate.delete(dish);
        assertTrue(voteHibernate.getById(vote.getId()) == null);
    }
}