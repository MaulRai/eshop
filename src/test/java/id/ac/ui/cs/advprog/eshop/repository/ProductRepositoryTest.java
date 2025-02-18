package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    private Product product;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
        product = new Product();
        product.setProductName("Test Product");
        product.setProductQuantity(10);
        productRepository.create(product);
    }

    @Test
    void testCreateAndFind() {
        productRepository = new ProductRepository();
        Product product = new Product();
        product.setProductId(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext()); //changed
    }

    @Test
    void testFindAllMoreThanOneProduct() {
        productRepository = new ProductRepository();
        Product product1 = new Product();
        product1.setProductId(UUID.fromString("dfc19748-f691-43ff-b2d4-5f7ad43e9ad5"));
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId(UUID.fromString("baecb1cc-d3d2-4c80-ab1c-8b14993656d7"));
        product2.setProductName("Reza Kecap");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testUpdate_ProductExists() {
        UUID productId = product.getProductId();
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(20);

        Product result = productRepository.update(productId, updatedProduct);

        assertNotNull(result);
        assertEquals("Updated Product", result.getProductName());
        assertEquals(20, result.getProductQuantity());
        assertNotEquals(30, result.getProductQuantity());
    }

    @Test
    void testUpdate_ProductDoesNotExist() {
        UUID nonExistentProductId = UUID.randomUUID();
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(20);

        Product result = productRepository.update(nonExistentProductId, updatedProduct);

        assertNull(result);
    }

    @Test
    void testDelete_ProductExists() {
        UUID productId = product.getProductId();

        productRepository.delete(productId);

        Product deletedProduct = productRepository.findById(productId);
        assertNull(deletedProduct);
    }

    @Test
    void testDelete_ProductDoesNotExist() {UUID nonExistentProductId = UUID.randomUUID();

        productRepository.delete(nonExistentProductId);

        Iterator<Product> products = productRepository.findAll();
        assertTrue(products.hasNext());
    }

}
