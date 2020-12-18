package com.revature.data;

import com.revature.beans.User;
import com.revature.exception.NonUniqueUsernameException;

import java.util.Set;

public class UserHibernate implements UserDAO {
    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public Set<User> getAll() {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User add(User u) throws NonUniqueUsernameException {
        return null;
    }
}
