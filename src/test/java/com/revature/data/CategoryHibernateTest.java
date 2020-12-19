package com.revature.data;

import com.revature.beans.Category;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryHibernateTest{
    private CategoryHibernate categoryHibernate = new CategoryHibernate();

    private Category category = new Category();

    @Test
    void getById(){
        category.setId(1);
        category.setCategory("soup");
        assertEquals(category, categoryHibernate.getById(category.getId()));
    }

    @Test
    void getAll(){
        assertTrue(categoryHibernate.getAll().size() > 0);
    }

}