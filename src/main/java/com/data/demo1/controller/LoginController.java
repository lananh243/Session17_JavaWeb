package com.data.demo1.controller;

import com.data.demo1.dto.LoginDTO;
import com.data.demo1.entity.Customer;
import com.data.demo1.entity.Product;
import com.data.demo1.entity.ProductCart;
import com.data.demo1.service.CartService;
import com.data.demo1.service.LoginService;
import com.data.demo1.service.ProductCartService;
import com.data.demo1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCartService productCartService;

    @Autowired
    private CartService cartService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "login";
    }

    @PostMapping("/save-login")
    public String saveLogin(@Valid LoginDTO loginDTO,
                            BindingResult result, Model model,
                            HttpSession session) {

        if (result.hasErrors()) {
            return "login";
        }

        Customer customer = loginService.findByCustomer(
                loginDTO.getUsername(), loginDTO.getPassword());

        if (customer == null) {
            model.addAttribute("error", "Tên người dùng hoặc mật khẩu không đúng");
            return "login";
        }

        session.setAttribute("customer", customer);

        // Điều hướng theo vai trò
        if ("ADMIN".equalsIgnoreCase(customer.getRole())) {
            model.addAttribute("username", customer.getUsername());
            return "admin";
        } else if ("USER".equalsIgnoreCase(customer.getRole())) {
            model.addAttribute("username", customer.getUsername());
            return "redirect:/products";
        }

        // Nếu role không khớp
        model.addAttribute("error", "Quyền không hợp lệ");
        return "login";
    }

    @GetMapping("/products")
    public String listProducts(@RequestParam(defaultValue = "1") int page,
                               Model model
    ) {
        int pageSize = 5;
        List<Product> products = productService.findAll(page, pageSize);
        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        return "product_list";
    }

    @GetMapping("/products/{id}")
    public String showProduct(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "product_detail";
    }

    @PostMapping("/add-to-cart/{productId}")
    public String addToCart(@PathVariable int productId,
                            @RequestParam(defaultValue = "1") int quantity,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            redirectAttributes.addFlashAttribute("error", "Bạn cần đăng nhập để thêm vào giỏ hàng.");
            return "redirect:/login";
        }

        productCartService.addProduct(customer.getId(), productId, quantity);
        redirectAttributes.addFlashAttribute("message", "Thêm sản phẩm vào giỏ hàng thành công!");
        return "redirect:/products/" + productId;
    }

    // Hiển thị giỏ hàng
    @GetMapping("/cart")
    public String showCart(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            redirectAttributes.addFlashAttribute("error", "Bạn cần đăng nhập để xem giỏ hàng.");
            return "redirect:/login";
        }

        List<ProductCart> cartItems = productCartService.findAllByCustomerId(customer.getId());
        model.addAttribute("cartItems", cartItems);

        double totalPrice = 0.0;
        for (ProductCart item : cartItems) {
            Product product = productService.findById(item.getProductId());
            if (product != null) {
                totalPrice += item.getQuantity() * product.getPrice();
            }
        }

        model.addAttribute("totalPrice", totalPrice);
        return "cart_list";
    }


    @PostMapping("/delete-cart")
    public String deleteCart(@RequestParam("cartId") int cartId,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            redirectAttributes.addFlashAttribute("error", "Bạn cần đăng nhập để xóa sản phẩm trong giỏ hàng.");
            return "redirect:/login";
        }

        cartService.delete(cartId);
        redirectAttributes.addFlashAttribute("message", "Xóa sản phẩm khỏi giỏ hàng thành công.");
        return "redirect:/cart";
    }

    @PostMapping("/update-cart")
    public String updateCartQuantity(@RequestParam int cartId,
                                     @RequestParam int quantity,
                                     HttpSession session,
                                     RedirectAttributes redirectAttributes) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            redirectAttributes.addFlashAttribute("error", "Bạn cần đăng nhập để cập nhật giỏ hàng.");
            return "redirect:/login";
        }

        cartService.updateQuantityById(cartId, quantity);
        redirectAttributes.addFlashAttribute("message", "Cập nhật số lượng thành công!");
        return "redirect:/cart";
    }


}
