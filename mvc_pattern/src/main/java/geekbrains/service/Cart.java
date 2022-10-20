package geekbrains.service;

import geekbrains.base.Product;

import java.util.List;

public interface Cart {
    boolean addProduct(int id);
    boolean remove(int id);
    List<Product> getProducts();
}
