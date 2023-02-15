package com.example.shopping_project.Repositry;

import com.example.shopping_project.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositry extends JpaRepository<Product,Integer> {
    Product findProductById(Integer id);
}
