package com.revature.services;

import com.revature.beans.Category;
import com.revature.beans.Dish;
import com.revature.beans.User;
import com.revature.beans.Vote;
import com.revature.exception.AlreadyVotedException;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Set;

public interface VoteService {
    // Create
    public Vote addVote(Vote v) throws AlreadyVotedException;

    // Read
    public Vote getVoteById(Integer id);
    public Set<Vote> getAll();

    // Update
    public void updateVote(Vote v);
    public void categoryVote(User u, Dish d, Category c) throws AlreadyVotedException;

    // Delete
    public void deleteVote(Vote v);
}
