package org.example.graphql_baitap08.graphql;

import org.example.graphql_baitap08.model.Product;
import org.example.graphql_baitap08.model.User;
import org.example.graphql_baitap08.model.Category;
import org.example.graphql_baitap08.repository.ProductRepository;
import org.example.graphql_baitap08.repository.UserRepository;
import org.example.graphql_baitap08.repository.CategoryRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class QueryResolver {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public QueryResolver(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @QueryMapping
    public List<Product> productsByPriceAsc() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    @QueryMapping
    public List<Product> productsByCategory(@Argument Integer categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    @QueryMapping
    public List<Product> products() { return productRepository.findAll(); }

    @QueryMapping
    public Product product(@Argument Integer id) { return productRepository.findById(id).orElse(null); }

    @QueryMapping
    public List<User> users() { return userRepository.findAll(); }

    @QueryMapping
    public User user(@Argument Integer id) { return userRepository.findById(id).orElse(null); }

    @QueryMapping
    public List<Category> categories() { return categoryRepository.findAll(); }

    @QueryMapping
    public Category category(@Argument Integer id) { return categoryRepository.findById(id).orElse(null); }
}


