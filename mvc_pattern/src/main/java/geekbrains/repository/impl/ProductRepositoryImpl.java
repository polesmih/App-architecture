package geekbrains.repository.impl;

import geekbrains.base.Product;
import geekbrains.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository

public class ProductRepositoryImpl implements ProductRepository {

    private final List<Product> products = new ArrayList<>();
    private int id;

    @PostConstruct

    private void init() {
        for (int i = 0; i < 5; i++) {
            products.add(new Product(i + 1, "Product" + (i + 1), (i + 1)*10));
    }
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Product findById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        id++;
        product.setId(id);
        products.add(product);
        return product;
    }
}
