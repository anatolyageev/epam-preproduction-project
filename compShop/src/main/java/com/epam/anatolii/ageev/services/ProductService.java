package com.epam.anatolii.ageev.services;

import com.epam.anatolii.ageev.bean.ProductFilterBean;
import com.epam.anatolii.ageev.domain.Product;
import java.util.List;

public interface ProductService {

    List<Product> getByFilter(ProductFilterBean productFilterBean);

    public Long getCountByFilter(ProductFilterBean productFilterBean);

    Product getOne(Long id);

    List<String> getAllProducers();

    List<String> getAllCategories();

}
