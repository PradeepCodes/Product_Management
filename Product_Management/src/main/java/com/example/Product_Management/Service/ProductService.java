package com.example.Product_Management.Service;

import com.example.Product_Management.Entity.Product;
import com.example.Product_Management.Repository.ProductRepository;

import java.util.List;

public interface ProductService {
    void saveProduct(Product product);
    List<Product> getAllProducts();

}
