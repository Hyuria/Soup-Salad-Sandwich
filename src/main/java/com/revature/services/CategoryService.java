package com.revature.services;

import java.util.Set;
import com.revature.beans.Category;

public interface CategoryService{
   // create
   public Integer addCategory(Category c);

   // read
   public Category getCategoryById(Integer id);
   public Set<Category> getAllCategories();

   // update
   public void updateCategory(Category c);

   // delete
   public void deleteCategory(Category c);
}
