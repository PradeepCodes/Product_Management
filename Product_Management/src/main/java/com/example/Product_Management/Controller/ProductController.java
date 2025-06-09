package com.example.Product_Management.Controller;

import com.example.Product_Management.Entity.Product;
import com.example.Product_Management.Exception.ProductNotFoundException;
import com.example.Product_Management.Service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class  ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home()
    {
        return "home";
    }
    @GetMapping("/addProduct")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new ProductNotFoundException("Product name cannot be empty");
        }
        productService.saveProduct(product);
        return "redirect:/";
    }

    @GetMapping("/display")
    public String displayProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found in the database.");
        }
        model.addAttribute("products", products);
        return "displayProduct";
    }
    @GetMapping("/error-test")
    public String errorTest() {
        throw new ProductNotFoundException("Test Product Not Found Error!");
    }

}
