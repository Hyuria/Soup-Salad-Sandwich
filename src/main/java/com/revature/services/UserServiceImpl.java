package com.revature.services;

import java.util.Set;
import com.revature.exception.NonUniqueUsernameException;
import com.revature.beans.User;
import com.revature.data.UserHibernate;
import com.revature.data.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
   private UserDAO userDAO;

   @Autowired
   public UserServiceImpl(UserDAO u){
      userDAO = u;
   }

   @Override
   public User addUser(User u) throws NonUniqueUsernameException{
      return userDAO.add(u);
   }

   @Override
   public User getUserById(Integer id){
      return userDAO.getById(id);
   }

   @Override
   public Set<User> getAllUsers(){
      return userDAO.getAll();
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
