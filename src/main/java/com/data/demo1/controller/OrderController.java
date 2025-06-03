package com.data.demo1.controller;

import com.data.demo1.entity.Customer;
import com.data.demo1.entity.Order;
import com.data.demo1.entity.Product;
import com.data.demo1.entity.ProductCart;
import com.data.demo1.service.CustomerService;
import com.data.demo1.service.OrderService;
import com.data.demo1.service.ProductCartService;
import com.data.demo1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {

    @Autowired
    private ProductCartService productCartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    // Hiển thị form thanh toán
    @GetMapping("/checkout")
    public String showCheckoutForm(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            redirectAttributes.addFlashAttribute("error", "Bạn cần đăng nhập để thanh toán.");
            return "redirect:/login";
        }

        List<ProductCart> cartItems = productCartService.findAllByCustomerId(customer.getId());
        if (cartItems.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Giỏ hàng của bạn đang trống.");
            return "redirect:/cart";
        }

        double total = cartItems.stream()
                .mapToDouble(item -> {
                    Product product = productService.findById(item.getProductId());
                    return item.getQuantity() * product.getPrice();
                })
                .sum();

        List<String> products = cartItems.stream()
                .map(item -> String.valueOf(item.getProductId()))
                .collect(Collectors.toList());

        Order order = new Order();
        order.setCustomerId(customer.getId());
        order.setTotalMoney(total);
        order.setListProduct(products);

        model.addAttribute("order", order);
        model.addAttribute("totalPrice", total);
        return "checkout"; // Trả về file checkout.html
    }

    // Xử lý submit form thanh toán
    @PostMapping("/checkout")
    public String handleCheckout(Order order,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            redirectAttributes.addFlashAttribute("error", "Bạn cần đăng nhập để thanh toán.");
            return "redirect:/login";
        }

        order.setCustomerId(customer.getId());
        order.setStatus("Đang xử lý");

        orderService.save(order);
        productCartService.clearCartByCustomerId(customer.getId());

        redirectAttributes.addFlashAttribute("message", "Đặt hàng thành công!");
        return "redirect:/products";
    }

    @GetMapping("/profile")
    public String showProfileForm(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        // Lấy customer từ session (đã đăng nhập)
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            redirectAttributes.addFlashAttribute("error", "Bạn cần đăng nhập để xem trang cá nhân.");
            return "redirect:/login";
        }

        customer = customerService.findById(customer.getId());

        List<Order> orders = orderService.findByCustomerId(customer.getId());

        model.addAttribute("customer", customer);
        model.addAttribute("orders", orders);

        return "profile";
    }

}
