//package com.cake.cakeshop.Controller;
//
//import com.cake.cakeshop.Model.AddItem;
//import com.cake.cakeshop.Repo.ProductService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpSession;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//public class CartController {
//
//    private final ProductService productService;
//
//    public CartController(ProductService productService) {
//        this.productService = productService;
//    }
//
//    @PostMapping("/add-to-cart")
//    public String addToCart(@RequestParam("productId") Integer productId, HttpSession session) {
//        Optional<AddItem> productOptional = productService.findById(productId);
//        if (productOptional.isPresent()) {
//            AddItem product = productOptional.get();
//
//            if (product != null) {
//                // Retrieve the cart items from the session or create a new list if it doesn't exist
//                List<AddItem> cartItems = (List<AddItem>) session.getAttribute("cartItems");
//                if (cartItems == null) {
//                    cartItems = new ArrayList<>();
//                    session.setAttribute("cartItems", cartItems);
//                }
//                // Add the selected product to the cart
//                cartItems.add(product);
//            }
//            return "redirect:/cart"; // Redirect to the cart page or any other appropriate page
//        }
//
//        // Handle the case when the product is not found
//        return "redirect:/products"; // Redirect to the products page or any other appropriate page
//    }
//}
