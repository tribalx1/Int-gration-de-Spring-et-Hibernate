package com.example;

import com.example.dao.IDao;
import com.example.entities.Category;
import com.example.entities.Product;
import com.example.util.HibernateConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfig.class)
@Transactional
public class ProductDaoTest {

    @Autowired
    @Qualifier("productDaoImpl")
    private IDao<Product> productDao;
    
    @Autowired
    @Qualifier("categoryDaoImpl")
    private IDao<Category> categoryDao;
    
    @Test
    public void testCreateAndFindProduct() {
        // Créer une catégorie
        Category category = new Category();
        category.setName("Test Category");
        category.setDescription("Test Description");
        categoryDao.create(category);
        
        // Créer un produit associé à la catégorie
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100.0);
        product.setCategory(category);
        
        // Sauvegarder le produit
        productDao.create(product);
        
        // Récupérer le produit et vérifier
        Product foundProduct = productDao.findById(product.getId());
        Assert.assertNotNull(foundProduct);
        Assert.assertEquals("Test Product", foundProduct.getName());
        Assert.assertEquals(100.0, foundProduct.getPrice(), 0.001);
        Assert.assertNotNull(foundProduct.getCategory());
        Assert.assertEquals("Test Category", foundProduct.getCategory().getName());
    }
}