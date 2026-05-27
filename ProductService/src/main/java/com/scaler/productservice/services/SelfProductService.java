package com.scaler.productservice.services;

import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service("selfProductService") // Name written in @Service Annotation will be used in Qualifier Annotation in the Controller
public class SelfProductService implements ProductService {

    ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(optionalProduct.isEmpty()) {
            throw new ProductNotFoundException(productId);
        }

        return optionalProduct.get();
    }

    public Page<Product> getProductsByTitle(String title, int pageNumber, int pageSize) {

        // This sort object is used to get the sorted result from the database
        Sort sort = Sort.by(Sort.Direction.DESC, "price")
                .and(Sort.by(Sort.Direction.ASC, "title"));

        // This is an object which is used to enable pagination
        // 'PageRequest' class is the implementation of 'Pageable' interface, which provides pagination
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        return productRepository.findByTitleContainsIgnoreCase(title, pageRequest);
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Void deleteProduct(Long productId) {
        return null;
    }
}
