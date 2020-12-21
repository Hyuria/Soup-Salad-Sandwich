package com.revature.services;

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
    public void deleteVote(Vote v) {
        voteDAO.delete(v);
    }
}
