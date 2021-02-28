package com.epam.anatolii.ageev.services.impl;

import com.epam.anatolii.ageev.bean.ProductFilterBean;
import com.epam.anatolii.ageev.domain.Product;
import com.epam.anatolii.ageev.repository.ProductRepository;
import com.epam.anatolii.ageev.repository.transaction.TransactionManager;
import com.epam.anatolii.ageev.repository.transaction.impl.TransactionManagerImpl;
import com.epam.anatolii.ageev.services.ProductService;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final TransactionManager transactionManager;

    public ProductServiceImpl(ProductRepository productRepository, DataSource dataSource) {
        this.transactionManager = new TransactionManagerImpl(dataSource);
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getByFilter(ProductFilterBean productFilterBean) {
        return transactionManager.executeDqlTransaction(() -> productRepository.getByFilter(productFilterBean));
    }

    @Override
    public Long getCountByFilter(ProductFilterBean productFilterBean) {
        return transactionManager.executeDqlTransaction(() -> productRepository.getCountByFilter(productFilterBean));
    }

    @Override
    public Product getOne(Long id) {
        return transactionManager.executeDqlTransaction(() -> productRepository.getOne(id));
    }

    @Override
    public List<String> getAllProducers() {
        return transactionManager.executeDqlTransaction(productRepository::getAllProducers);
    }

    @Override
    public List<String> getAllCategories() {
        return transactionManager.executeDqlTransaction(productRepository::getAllCategories);
    }
}
