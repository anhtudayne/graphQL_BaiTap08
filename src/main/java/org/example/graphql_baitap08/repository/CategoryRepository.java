package org.example.graphql_baitap08.repository;

import org.example.graphql_baitap08.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}


