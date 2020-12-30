package com.revature.services;

import com.revature.beans.Category;
import com.revature.beans.Dish;
import com.revature.beans.User;
import com.revature.beans.Vote;
import com.revature.data.CategoryDAO;
import com.revature.data.DishDAO;
import com.revature.data.VoteDAO;
import com.revature.data.VoteHibernate;
import com.revature.exception.AlreadyVotedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class VoteServiceImpl implements VoteService{
    private VoteDAO voteDAO;
    private CategoryDAO categoryDAO;
    private DishDAO dishDAO;

    @Autowired
    public VoteServiceImpl(VoteDAO v, CategoryDAO c, DishDAO d){
        voteDAO = v;
        categoryDAO = c;
        dishDAO = d;
    }

    @Override
    public Vote addVote(Vote v) throws AlreadyVotedException {
        return voteDAO.add(v);
    }

    @Override
    public Vote getVoteById(Integer id) {
        return voteDAO.getById(id);
    }

    @Override
    public Set<Vote> getAll() {
        return voteDAO.getAll();
    }

    @Override
    public Set<Vote> getVoteByDishId(Integer id) {
        Set<Vote> allVote = getAll();
        Set<Vote> retSet = new HashSet<>();
        for(Vote v : allVote){
            if (v.getDish().getId() == id){
                retSet.add(v);
            }
        }
        return retSet;
    }

    @Override
    public void updateVote(Vote v) {
        voteDAO.update(v);
    }

    @Override
    public void categoryVote(User u, Dish d, Category c) throws AlreadyVotedException {
        Set<Vote> voteSet = voteDAO.getAll();
        Vote existingVote = null;

        for (Vote v : voteSet){
            if (v.getUser().getId() == u.getId() && v.getDish().getId() == d.getId()){
                existingVote = v;
            }
        }

        if (existingVote == null){
            // If user did not vote for this dish yet
            Vote vote = new Vote();
            vote.setCategory(c);
            vote.setUser(u);
            vote.setDish(d);
            voteDAO.add(vote);
        }else{
            // If the user already voted for this dish already
            if (existingVote.getCategory().getId() != c.getId()){
                existingVote.setCategory(c);
                voteDAO.update(existingVote);
            }else{
                throw new AlreadyVotedException();
            }
            // If the vote is by the same user, for the same dish and category, nothing changes.
        }

        // Reclassify Dish Category

        Integer soupVoteCount = 0;
        Integer saladVoteCount = 0;
        Integer sandwichVoteCount = 0;

        voteSet = voteDAO.getAll();

        for (Vote v : voteSet){
            if (v.getDish().getId() == d.getId()){
                if (v.getCategory().getId() == 1){
                    soupVoteCount ++;
                }else if (v.getCategory().getId() == 2){
                    saladVoteCount ++;
                }else if (v.getCategory().getId() == 3){
                    sandwichVoteCount ++;
                }
            }
        }

        if (soupVoteCount > saladVoteCount && soupVoteCount > sandwichVoteCount){
            d.setCategory(categoryDAO.getById(1));
        }
        else if (saladVoteCount > soupVoteCount && saladVoteCount > sandwichVoteCount){
            d.setCategory(categoryDAO.getById(2));
        }
        else if (sandwichVoteCount > soupVoteCount && sandwichVoteCount > saladVoteCount){
            d.setCategory(categoryDAO.getById(3));
        }
        else{
            d.setCategory(categoryDAO.getById(4));
        }
        dishDAO.update(d);
    }

    @Override
    public void deleteVote(Vote v) {
        voteDAO.delete(v);
    }
}
