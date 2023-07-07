package com.cake.cakeshop.Controller;
import com.cake.cakeshop.Model.AddItem;
 import com.cake.cakeshop.Model.User;
import com.cake.cakeshop.Repo.ProductService;
import com.cake.cakeshop.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductService productService;
//    private List<Product> cartItems = new ArrayList<>();
//    session.setAttribute("cartItems", new ArrayList<AddItem>());

    @GetMapping("/index")
    public String index(){

        return "index";
    }
    @GetMapping("/DisplayAdmin")
    public String display(Model model) {
        List<AddItem> products = productService.findAll();
   model.addAttribute("products", products);
        return "DisplayAdmin";
    }
    @PostMapping("/register")
    public String userRegistration(@RequestParam("Username") String username, @RequestParam("Password") String password) {
        if (username.equals("admin") && password.equals("pass")) {
            return "redirect:/AdminMenu"; // Redirect to Admin menu
        } else {
            return "redirect:/UserMenu"; // Redirect to UserMenu page
        }
    }

    @GetMapping("/UserMenu")
    public String userMenuPage() {
        // Retrieve the user's information from the session or database
//        User user = getUserFrom(session); // Replace this with your own method to retrieve the user

        // Add the user's information to the model

        return "UserMenu"; // Return the view name for the UserMenu page
    }

    @RequestMapping("/Home")
    public String Home(Model model)
    {
        List<AddItem> products = productService.findAll();
        model.addAttribute("products", products);
        return "UserMenu";
    }

    @RequestMapping("/ContactUs")
    public String Contact()
    {
        return "ContactUs";
    }
    @RequestMapping("/AboutUs")
    public String AboutUs()
    {
        return "AboutUs";
    }
    @RequestMapping("/ProductDisplay")
    public String ProductDisplay(Model model)
    {

        return "ProductDisplay";
    }
    @RequestMapping("/AdminMenu")
    public String AdminMenu()
    {
        return "AdminMenu";
    }
    @GetMapping("/Logout")
    public String Logout(HttpSession session) {
        // Invalidate the session and redirect to the login page
        session.invalidate();
        return "redirect:/index";
    }
    @RequestMapping("/AddProduct")
    public String AddProduct()
    {
        return "AddProduct";
    }
@PostMapping("/addProduct")
public String addProduct( AddItem addItem, Model model) {
    // Save the product to the database using the repository or service
    productService.save(addItem);
    // Redirect to a success page or display a success message
    return "redirect:/DisplayAdmin";
}

//update vaues
    @GetMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable("id") Integer Id, Model model)
    {
        Optional<AddItem> product = productService.findById(Id);
        if(product.isPresent()){
            System.out.println("ara hai ki nai");
            model.addAttribute("value",product.get());
            return "editProduct";
        }
//        return "redirect:/error";
        return "redirect:/editProduct";
    }
    @PostMapping("/updateProductNew")
    public String productUpdation(@ModelAttribute("value") AddItem addItem) {
        productService.save(addItem);
        return "redirect:/DisplayAdmin";
    }

//@GetMapping("/error")
//public String handleError() {
//    return "error";
//}
    @GetMapping("/editProduct")
    public String editProductPage() {
        return "editProduct";
    }
//delete products
    @PostMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteById(id);
        return "redirect:/DisplayAdmin";
    }
    @GetMapping("/deleteProduct/{id}")
    public String deleteProductnew(@PathVariable("id") Integer id) {
        productService.deleteById(id);
        return "redirect:/DisplayAdmin";
    }
    @PostMapping("/UserMenu")
    public String userMenuPagenew(Model model, HttpSession session) {
        List<AddItem> products = productService.findAll();
        model.addAttribute("products", products);

        return "ProductDisplay";
    }
//    @RequestMapping("/UserMenu")
//    public String userMen(Model model, HttpSession session) {
//        List<AddItem> products = productService.findAll();
//        model.addAttribute("products", products);
//
//        return "redirect:/UserMenu";
//    }

    @GetMapping("/ProductDisplay")
    public String productDisplay(Model model,HttpSession session) {
        List<AddItem> products = productService.findAll();
        System.out.println("Products: " + products); // Print the products to the console for debugging
        model.addAttribute("products", products);
        // Retrieve the cart items from the session
        List<AddItem> cartItems = (List<AddItem>) session.getAttribute("cartItems");

        // Add the cartItems to the model
        model.addAttribute("cartItems", cartItems);

        return "ProductDisplay";
    }

//cart here

    @GetMapping("/cart")
    public String cartPage(HttpSession session, Model model) {
        List<AddItem> cartItems = (List<AddItem>) session.getAttribute("cartItems");
//        if (cartItems == null) {
//            cartItems = new ArrayList<>();
//            session.setAttribute("cartItems", cartItems);
//        }
        model.addAttribute("cartItems", cartItems);
        return "Cart";
    }


    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("productId") Integer productId, HttpSession session) {
        Optional<AddItem> productOptional = productService.findById(productId);
        if (productOptional.isPresent()) {
            AddItem product = productOptional.get();

            // Retrieve the cart items from the session or create a new list if it doesn't exist
            List<AddItem> cartItems = (List<AddItem>) session.getAttribute("cartItems");
            if (cartItems == null) {
                cartItems = new ArrayList<>();
                session.setAttribute("cartItems", cartItems);
            }
            // Add the selected product to the cart
            cartItems.add(product);
        }
        return "redirect:/Cart";
    }

    @RequestMapping("/\uD83D\uDED2")
    public String Cart()
    {
        return "Cart";
    }
//
//    @GetMapping("/demo")
//    public String demoPage(Model model) {
//        // Add any necessary model data here
//
//        return "demo";
//    }


}

