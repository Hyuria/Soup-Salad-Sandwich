package com.revature.services;

import java.util.Set;
import com.revature.data.CategoryHibernate;
import com.revature.data.CategoryDAO;
import com.revature.beans.Category;

public class CategoryServiceImpl implements CategoryService{
   private CategoryDAO categoryDAO;

   public CategoryServiceImpl(){
      categoryDAO = new CategoryHibernate();
   }

   @Override
   public Integer addCategory(Category c) {
      return categoryDAO.add(c).getId();
   }

   @Override
   public Category getCategoryById(Integer id){
      return categoryDAO.getById(id);
   }

   @Override
   public Set<Category> getAllCategories(){
      return categoryDAO.getAll();
   }

   @Override
   public void updateCategory(Category c){
      categoryDAO.update(c);
   }

   @Override
   public void deleteCategory(Category c){
      categoryDAO.delete(c);
   }
}
