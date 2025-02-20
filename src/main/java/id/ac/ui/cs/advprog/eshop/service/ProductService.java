package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;
import java.util.UUID;


public interface ProductService {
    Product create(Product product);
    Product update(UUID productId, Product product);
    void delete(UUID productId);
    List<Product> findAll();
    Product findById(UUID productId);

}
