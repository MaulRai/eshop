package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private Product product;
    private UUID productId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        // Sample product for testing
        productId = UUID.randomUUID();
        product = new Product();
        product.setProductId(productId);
        product.setProductName("Test Product");
        product.setProductQuantity(10);
    }

    @Test
    void testCreateProductPage_ReturnsCreateProductView() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProductPost_RedirectsToList() throws Exception {
        mockMvc.perform(post("/product/create")
                        .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(productService, times(1)).create(any(Product.class));
    }

    @Test
    void testProductListPage_ReturnsProductListView() throws Exception {
        when(productService.findAll()).thenReturn(Collections.singletonList(product));

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(model().attributeExists("products"));

        verify(productService, times(1)).findAll();
    }

    @Test
    void testEditProductPage_ReturnsEditProductView() throws Exception {
        when(productService.findById(productId)).thenReturn(product);

        mockMvc.perform(get("/product/edit/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(view().name("editProduct"))
                .andExpect(model().attributeExists("product"));

        verify(productService, times(1)).findById(productId);
    }

    @Test
    void testUpdateProductPost_RedirectsToList() throws Exception {
        mockMvc.perform(post("/product/update")
                        .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(productService, times(1)).update(eq(productId), any(Product.class));
    }

    @Test
    void testDeleteProduct_RedirectsToList() throws Exception {
        mockMvc.perform(get("/product/delete/{id}", productId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).delete(productId);
    }
}
