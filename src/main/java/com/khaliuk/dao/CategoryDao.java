package com.khaliuk.dao;

import com.khaliuk.model.Category;
import java.util.List;

public interface CategoryDao {
    List<Category> getAll();

    Category getById(Long id);

    Category save(Category category);
}
