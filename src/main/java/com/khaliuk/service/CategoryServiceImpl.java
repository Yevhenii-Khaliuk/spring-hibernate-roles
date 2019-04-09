package com.khaliuk.service;

import com.khaliuk.dao.CategoryDao;
import com.khaliuk.model.Category;
import com.khaliuk.model.Product;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductService productService;

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @Override
    public Category getById(Long id) {
        return categoryDao.getById(id);
    }

    @Override
    public Category reduceCategoryPriceByPercents(Category category, int percents) {
        List<Product> products = category.getProducts().stream()
                .map(p -> productService.reducePriceByPercents(p, percents))
                .collect(Collectors.toList());
        category.setProducts(products);
        return categoryDao.save(category);
    }
}
