package com.revature.services;

import com.revature.beans.Category;
import com.revature.beans.Dish;
import com.revature.beans.User;
import com.revature.beans.Vote;
import com.revature.data.VoteDAO;
import com.revature.data.VoteHibernate;
import com.revature.exception.AlreadyVotedException;

import java.util.Set;

public class VoteServiceImpl implements VoteService{
    private VoteDAO voteDAO;

    public VoteServiceImpl(){
        voteDAO = new VoteHibernate();
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
            }
            // If the vote is by the same user, for the same dish and category, nothing changes.
        }

    }

    @Override
    public void deleteVote(Vote v) {
        voteDAO.delete(v);
    }
}
