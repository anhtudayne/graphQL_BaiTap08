package org.example.graphql_baitap08.graphql;

import org.example.graphql_baitap08.model.Category;
import org.example.graphql_baitap08.model.Product;
import org.example.graphql_baitap08.model.User;
import org.example.graphql_baitap08.repository.CategoryRepository;
import org.example.graphql_baitap08.repository.ProductRepository;
import org.example.graphql_baitap08.repository.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller
public class MutationResolver {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public MutationResolver(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    // User CRUD
    @MutationMapping
    public User createUser(@Argument String fullname, @Argument String email, @Argument String password, @Argument String phone) {
        User u = new User();
        u.setFullname(fullname);
        u.setEmail(email);
        u.setPassword(password);
        u.setPhone(phone);
        return userRepository.save(u);
    }

    @MutationMapping
    public User updateUser(@Argument Integer id, @Argument String fullname, @Argument String email, @Argument String password, @Argument String phone) {
        User u = userRepository.findById(id).orElseThrow();
        if (fullname != null) u.setFullname(fullname);
        if (email != null) u.setEmail(email);
        if (password != null) u.setPassword(password);
        if (phone != null) u.setPhone(phone);
        return userRepository.save(u);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Integer id) {
        if (!userRepository.existsById(id)) return false;
        userRepository.deleteById(id);
        return true;
    }

    // Category CRUD
    @MutationMapping
    public Category createCategory(@Argument String name, @Argument String images) {
        Category c = new Category();
        c.setName(name);
        c.setImages(images);
        return categoryRepository.save(c);
    }

    @MutationMapping
    public Category updateCategory(@Argument Integer id, @Argument String name, @Argument String images) {
        Category c = categoryRepository.findById(id).orElseThrow();
        if (name != null) c.setName(name);
        if (images != null) c.setImages(images);
        return categoryRepository.save(c);
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Integer id) {
        if (!categoryRepository.existsById(id)) return false;
        categoryRepository.deleteById(id);
        return true;
    }

    // Product CRUD
    @MutationMapping
    public Product createProduct(@Argument String title, @Argument Integer quantity, @Argument String desc, @Argument BigDecimal price, @Argument Integer userId) {
        Product p = new Product();
        p.setTitle(title);
        p.setQuantity(quantity);
        p.setDesc(desc);
        p.setPrice(price);
        if (userId != null) {
            User u = userRepository.findById(userId).orElseThrow();
            p.setUser(u);
        }
        return productRepository.save(p);
    }

    @MutationMapping
    public Product updateProduct(@Argument Integer id, @Argument String title, @Argument Integer quantity, @Argument String desc, @Argument BigDecimal price, @Argument Integer userId) {
        Product p = productRepository.findById(id).orElseThrow();
        if (title != null) p.setTitle(title);
        if (quantity != null) p.setQuantity(quantity);
        if (desc != null) p.setDesc(desc);
        if (price != null) p.setPrice(price);
        if (userId != null) {
            User u = userRepository.findById(userId).orElseThrow();
            p.setUser(u);
        }
        return productRepository.save(p);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Integer id) {
        if (!productRepository.existsById(id)) return false;
        productRepository.deleteById(id);
        return true;
    }
}


