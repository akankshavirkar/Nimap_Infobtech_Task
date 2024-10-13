package com.cybersuccess;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/insert")
    public String insertCategory(@RequestParam("name") String name,
                                 @RequestParam("description") String description) {
        try {
            Category category = new Category();
            category.setName(name);
            category.setDescription(description);

            categoryService.saveCategory(category);  // Save the category
            return "Category data submitted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error submitting category data";
        }
    }
    
 // GET: Retrieve all categories
    @GetMapping("/all")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // GET: Retrieve a category by ID
    @GetMapping("/{id}")
    public Optional<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    // PUT: Update a category by ID
    @PutMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id,
                                 @RequestParam("name") String name,
                                 @RequestParam("description") String description) {
        try {
            Category category = new Category();
            category.setId(id);
            category.setName(name);
            category.setDescription(description);

            categoryService.updateCategory(category);
            return "Category updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating category";
        }
    }

    // DELETE: Delete a category by ID
    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return "Category deleted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting category";
        }
    }

    // OPTIONS: Show supported HTTP methods
    @RequestMapping(value = "/options", method = RequestMethod.OPTIONS)
    public String showOptions() {
        return "Supported methods: GET, POST, PUT, DELETE, OPTIONS";
    }
}
