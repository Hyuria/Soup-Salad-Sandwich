package com.revature.data;

import com.revature.beans.Vote;
import com.revature.exception.AlreadyVotedException;

public interface VoteDAO extends GenericDAO<Vote> {
    public Vote add(Vote v) throws AlreadyVotedException;
}
