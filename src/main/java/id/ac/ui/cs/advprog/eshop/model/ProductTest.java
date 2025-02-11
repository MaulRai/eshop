package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    Product product;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
    }

    @Test
    void testGetProductId() {
        assertEquals(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"), this.product.getProductId());
    }

    @Test
    void testGetProductId2() {
        assertNotEquals(UUID.fromString("b310c564-d1a1-4dbe-b72e-624855fecc41"), this.product.getProductId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Sampo Cap Bambang", this.product.getProductName());
    }

    @Test
    void testGetProductName2() {
        assertNotEquals("Reza Kecap", this.product.getProductName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.getProductQuantity());
    }

    @Test
    void testGetProductQuantity2() {
        assertNotEquals(1000, this.product.getProductQuantity());
    }
}


