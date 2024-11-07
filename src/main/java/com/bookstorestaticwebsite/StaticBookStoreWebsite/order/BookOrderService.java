package com.bookstorestaticwebsite.StaticBookStoreWebsite.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookOrderService {
    @Autowired
    private BookOrderRepository bookOrderRepository;


    public List<BookOrder> getAllBookOrder(){return bookOrderRepository.findAll();}

    public BookOrder getBookOrderById(int id){
        return bookOrderRepository.findById(id).orElse(null);
    }







}
