package com.revature.data;

import com.revature.beans.Category;

public interface CategoryDAO extends GenericDAO<Category>{
    public Category add(Category c);
}
