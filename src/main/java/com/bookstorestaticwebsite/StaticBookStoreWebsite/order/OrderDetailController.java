package com.bookstorestaticwebsite.StaticBookStoreWebsite.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/order")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;


    @GetMapping("/all")
    public String getAllOrders(Model model){
        model.addAttribute("title", "Orders List");
        model.addAttribute("ordersList", orderDetailService.getAllOrderDetail());
        return "admin/order-list";
    }


}
