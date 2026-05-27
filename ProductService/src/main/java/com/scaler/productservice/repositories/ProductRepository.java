package com.scaler.productservice.repositories;

import com.scaler.productservice.models.Product;
import com.scaler.productservice.projections.TitleAndPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // Defines below class as a repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long aLong);

    @Override
    List<Product> findAll();

    Product save(Product product);

    @Query("select p.title, p.price from Product p where p.id = 1")
    List<TitleAndPrice> findTitleAndPriceById(Long id);

    Optional<Product> findByCategory_Title(String title);

    Page<Product> findByTitleContainsIgnoreCase(String title, Pageable pageable);
}
