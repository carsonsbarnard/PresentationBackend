package com.bookstorestaticwebsite.StaticBookStoreWebsite.order;

import com.bookstorestaticwebsite.StaticBookStoreWebsite.book.Book;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name="orderdetails")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private OrderDetailID orderDetailID = new OrderDetailID();

    @NonNull
    private int quantity;
    @NonNull
    private float subtotal;

    @ManyToOne
    @JoinColumn(name="bookId", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name="bookOrderId", nullable = false)
    private BookOrder bookOrder;

    public OrderDetail() {
    }

    public OrderDetail(OrderDetailID orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public OrderDetail(OrderDetailID orderDetailID, int quantity, float subtotal, Book book, BookOrder bookOrder) {
        this.orderDetailID = orderDetailID;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.book = book;
        this.bookOrder = bookOrder;
    }

    public OrderDetail(int quantity, float subtotal, Book book, BookOrder bookOrder) {
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.book = book;
        this.bookOrder = bookOrder;
    }

    public OrderDetailID getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(OrderDetailID orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BookOrder getBookOrder() {
        return bookOrder;
    }

    public void setBookOrder(BookOrder bookOrder) {
        this.bookOrder = bookOrder;
    }
}
