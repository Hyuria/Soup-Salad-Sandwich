package com.revature.data;
import com.revature.beans.Category;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrder.OrderAnnotation.class)
public class CategoryHibernateTest{
    private CategoryHibernate categoryHibernate = new CategoryHibernate();

    private Category category = new Category();

    @Test
    @Order(1)
    void add(){
        category.setCategory("Test");
        Category retCategory = categoryHibernate.add(category);
        category.setId(retCategory.getId());
        assertEquals(category, retRole);
    }

    @Test
    @Order(2)
    void getById(){
        assertEquals(category, categoryHibernate.getById(category.getId()));
    }

    @Test
    @Order(3)
    void getAll(){
        assertTrue(categoryHibernate.getAll().size() > 0);
    }

    @Test
    @Order(4)
    void update(){
        category.setCategory("Update");
        categoryHibernate.update(category);
        assertEquals(category, categoryHibernate.getById(category.getId));
    }

    @Test
    @Order(5)
    void delete(){
        categoryHibernate.delete(category);
        assertTrue(categoryHibernate.getById(category.getId()) == null);
    }
}