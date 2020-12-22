package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;
import com.revature.data.CategoryDAO;
import com.revature.beans.Category;

@Service
public class CategoryServiceImpl implements CategoryService{
   private CategoryDAO categoryDAO;

   @Autowired
   public CategoryServiceImpl(CategoryDAO c){
      categoryDAO = c;
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
