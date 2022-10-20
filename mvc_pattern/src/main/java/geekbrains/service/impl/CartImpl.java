package geekbrains.service.impl;

import geekbrains.base.Product;
import geekbrains.service.Cart;
import geekbrains.service.ProductService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Service
@Scope(scopeName = SCOPE_PROTOTYPE)
public class CartImpl implements Cart {

    private final List<Product> products;

    private final ProductService productService;

    public CartImpl(ProductService productService) {
        this.productService = productService;
        this.products = new ArrayList<>();
    }

    @Override
    public boolean addProduct(int id) {
        Optional<Product> productOptional = Optional.ofNullable(productService.findById(id));
        if (productOptional.isPresent()) {
            products.add(productOptional.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(int id) {
        return products.removeIf(product -> product
                .getId() == id);
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }
}
