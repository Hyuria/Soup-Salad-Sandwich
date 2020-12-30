package com.revature.data;

import com.revature.exception.AlreadyVotedException;
import com.revature.exception.NonUniqueDishException;
import com.revature.exception.NonUniqueUsernameException;

import java.util.Set;

public interface GenericDAO  <T>{
    // CRUD operations (create, read, update, delete)
    public T add(T t) throws NonUniqueUsernameException, AlreadyVotedException;
    public T getById(Integer id);
    public Set<T> getAll();
    public void update(T t);
    public void delete(T t);
}
