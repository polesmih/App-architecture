package geekbrains.service;


import geekbrains.base.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product findById(int id);
    Product addProduct(Product product);
}
