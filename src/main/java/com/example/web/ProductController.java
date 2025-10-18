package com.example.web;

import com.example.dao.IDao;
import com.example.entities.Category;
import com.example.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    @Qualifier("productDaoImpl")
    private IDao<Product> productDao;
    
    @Autowired
    @Qualifier("categoryDaoImpl")
    private IDao<Category> categoryDao;
    
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productDao.findAll();
        model.addAttribute("products", products);
        return "product/list";
    }
    
    @GetMapping("/categories")
    public String listCategories(Model model) {
        List<Category> categories = categoryDao.findAll();
        model.addAttribute("categories", categories);
        return "category/list";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryDao.findAll());
        return "product/add";
    }
    
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product, @RequestParam int categoryId) {
        Category category = categoryDao.findById(categoryId);
        product.setCategory(category);
        productDao.create(product);
        return "redirect:/products";
    }
}