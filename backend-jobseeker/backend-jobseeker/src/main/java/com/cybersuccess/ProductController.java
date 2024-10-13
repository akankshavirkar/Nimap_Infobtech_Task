package com.cybersuccess;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/signup")

public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/insert")
    public String insertUser(@RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("price") int price,
                             @RequestParam("quantity") int quantity)
                            {
        try {
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setQuantity(quantity);
          

            // userService.saveUser(user, resumeFile);
            return "User data submitted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error submitting user data";
        }
    }
    
 // GET: Retrieve all products
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET: Retrieve a product by ID
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // PUT: Update a product by ID
    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                @RequestParam("name") String name,
                                @RequestParam("description") String description,
                                @RequestParam("price") int price,
                                @RequestParam("quantity") int quantity) {
        try {
            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setQuantity(quantity);

            productService.updateProduct(product);
            return "Product updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating product";
        }
    }

    // DELETE: Delete a product by ID
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return "Product deleted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting product";
        }
    }

    // OPTIONS: Show supported HTTP methods
    @RequestMapping(value = "/options", method = RequestMethod.OPTIONS)
    public String showOptions() {
        return "Supported methods: GET, POST, PUT, DELETE, OPTIONS";
    }
}
