package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product testProduct;
    private UUID productId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productId = UUID.randomUUID();
        testProduct = new Product();
        testProduct.setProductId(productId);
        testProduct.setProductName("Test Product");
        testProduct.setProductQuantity(10);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(testProduct)).thenReturn(testProduct);

        Product createdProduct = productService.create(testProduct);

        assertNotNull(createdProduct);
        assertEquals(testProduct.getProductName(), createdProduct.getProductName());
        verify(productRepository, times(1)).create(testProduct);
    }

    @Test
    void testUpdateProductExists() {
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(20);

        when(productRepository.update(productId, updatedProduct)).thenReturn(updatedProduct);

        Product result = productService.update(productId, updatedProduct);

        assertNotNull(result);
        assertEquals("Updated Product", result.getProductName());
        assertEquals(20, result.getProductQuantity());
        verify(productRepository, times(1)).update(productId, updatedProduct);
    }

    @Test
    void testUpdateProductDoesNotExist() {
        when(productRepository.update(productId, testProduct)).thenReturn(null);

        Product result = productService.update(productId, testProduct);

        assertNull(result);
        verify(productRepository, times(1)).update(productId, testProduct);
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).delete(productId);

        productService.delete(productId);

        verify(productRepository, times(1)).delete(productId);
    }

    @Test
    void testFindAllProducts() {
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(testProduct);
        mockProducts.add(new Product());

        when(productRepository.findAll()).thenReturn(mockProducts.iterator());

        List<Product> result = productService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdProductExists() {
        when(productRepository.findById(productId)).thenReturn(testProduct);

        Product result = productService.findById(productId);

        assertNotNull(result);
        assertEquals(testProduct.getProductName(), result.getProductName());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testFindByIdProductDoesNotExist() {
        when(productRepository.findById(productId)).thenReturn(null);

        Product result = productService.findById(productId);

        assertNull(result);
        verify(productRepository, times(1)).findById(productId);
    }
}
