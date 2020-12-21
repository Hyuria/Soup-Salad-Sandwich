package com.revature.services;

import com.revature.exception.NonUniqueUsernameException;
import com.revature.beans.User;
import com.revature.data.UserHibernate;
import com.revature.data.UserDAO;

public class UserServiceImpl implements UserService{
   private UserDAO userDAO;

   public UserServiceImpl(){
      userDAO = new UserHibernate();
   }

   @Override
   public Integer addUser(User u) throws NonUniqueUsernameException{
      return userDAO.add(u).getId();
   }

   @Override
   public User getUserById(Integer id){
      return userDAO.getById(id);
   }

   @Override
   public User getUserByUsername(String username){
      return userDAO.getByUsername(username);
   }

   @Override
   public void updateUser(User u){
      userDAO.update(u);
   }

   @Override
   public void deleteUser(User u){
      userDAO.delete(u);
   }
}
