package com.epam.anatolii.ageev.repository;

import com.epam.anatolii.ageev.bean.ProductFilterBean;
import com.epam.anatolii.ageev.domain.Product;
import java.util.List;

public interface ProductRepository {
    List<Product> getByFilter(ProductFilterBean productFilterBean);

    public Long getCountByFilter(ProductFilterBean productFilterBean);

    Product getOne(Long id);

    List<String> getAllProducers();

    List<String> getAllCategories();
}
