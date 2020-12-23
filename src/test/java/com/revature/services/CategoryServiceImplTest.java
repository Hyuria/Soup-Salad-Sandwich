package com.revature.services;

import com.revature.beans.Category;
import com.revature.data.CategoryHibernate;
import com.revature.data.CategoryDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceImplTest {
   private CategoryDAO categoryDAO = new CategoryHibernate();
   private CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl(categoryDAO);
   private Category category = new Category();

   @Test
   void getCategoryById() {
      category.setId(1);
      category.setCategory("soup");
      assertEquals(category, categoryServiceImpl.getCategoryById(category.getId()));
   }

   @Test
   void getAllCategories() {
      assertTrue(categoryServiceImpl.getAllCategories().size() > 0);
   }

}
