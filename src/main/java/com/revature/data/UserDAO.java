package com.revature.data;

import com.revature.beans.User;
import com.revature.exception.NonUniqueUsernameException;

public interface UserDAO extends GenericDAO<User> {
    public User add(User u) throws NonUniqueUsernameException;
}
