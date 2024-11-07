package com.bookstorestaticwebsite.StaticBookStoreWebsite.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<OrderDetail> getAllOrderDetail(){return orderDetailRepository.findAll();}

    public OrderDetail getOrderDetailById(int id){
        return orderDetailRepository.findById(id).orElse(null);
    }

    public void updateOrderDetail(OrderDetail order, int id){
        OrderDetail existing = getOrderDetailById(id);
        existing.setBook(order.getBook());
        existing.setBookOrder(order.getBookOrder());
        existing.setQuantity(order.getQuantity());
        existing.setSubtotal(order.getSubtotal());
        orderDetailRepository.save(existing);

    }

    public void deleteOrder(int id){
        orderDetailRepository.deleteById(id);
    }








}
