package com.revature.services;

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

    // Delete
    public void deleteVote(Vote v);
}
