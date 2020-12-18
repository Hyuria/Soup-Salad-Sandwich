package com.revature.data;

import com.revature.beans.Vote;
import com.revature.exception.AlreadyVotedException;

import java.util.Set;

public class VoteHibernate implements VoteDAO{
    @Override
    public Vote getById(Integer id) {
        return null;
    }

    @Override
    public Set<Vote> getAll() {
        return null;
    }

    @Override
    public void update(Vote vote) {

    }

    @Override
    public void delete(Vote vote) {

    }

    @Override
    public Vote add(Vote v) throws AlreadyVotedException {
        return null;
    }
}
