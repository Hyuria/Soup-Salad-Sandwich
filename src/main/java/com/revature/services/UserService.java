package com.revature.services;

import com.revature.exception.NonUniqueUsernameException;
import com.revature.beans.User;

public interface UserService{
   // create
   public User addUser(User u) throws NonUniqueUsernameException;

   // read
   public User getUserById(Integer id);
   public User getUserByUsername(String username);

   // update
   public void updateUser(User u);

   // delete
   public void deleteUser(User u);
}
