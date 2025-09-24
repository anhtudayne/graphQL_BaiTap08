package org.example.graphql_baitap08.repository;

import org.example.graphql_baitap08.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByOrderByPriceAsc();

    @Query("select p from Product p join p.user u join u.categories c where c.id = :categoryId")
    List<Product> findAllByCategoryId(@Param("categoryId") Integer categoryId);
}


