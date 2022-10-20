package geekbrains.controller;


import geekbrains.base.Product;
import geekbrains.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @GetMapping("/addProduct")
    private String addFormAddProduct(Model model){
        Product product = new Product();
        model.addAttribute("products", product);
        return "addProduct";
    }

    @PostMapping("/addProduct")
    private String addProduct(@ModelAttribute Product product){
        productService.addProduct(product);
        return "redirect:/product";
    }

    @GetMapping
    private String getAllProduct(Model model){
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "product";

    }


}
