package com.bookstorestaticwebsite.StaticBookStoreWebsite.useractions;

import com.bookstorestaticwebsite.StaticBookStoreWebsite.book.Book;
import com.bookstorestaticwebsite.StaticBookStoreWebsite.book.BookService;
import com.bookstorestaticwebsite.StaticBookStoreWebsite.customer.Customer;
import com.bookstorestaticwebsite.StaticBookStoreWebsite.customer.CustomerService;
import com.bookstorestaticwebsite.StaticBookStoreWebsite.order.BookOrder;
import com.bookstorestaticwebsite.StaticBookStoreWebsite.order.BookOrderRequest;
import com.bookstorestaticwebsite.StaticBookStoreWebsite.review.Review;
import com.bookstorestaticwebsite.StaticBookStoreWebsite.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import java.util.List;

@RestController
@RequestMapping("/user")
public class CustomerUserController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;

    // Endpoint for users to create a customer profile
    @PostMapping("/add")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    // Endpoint for users to update their profile
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(customer, id);
        return ResponseEntity.ok(updatedCustomer);
    }

    // Endpoint to get available books
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // Endpoint for users to place a book order
    @PostMapping("/{customerId}/orders/{bookId}")
    public ResponseEntity<BookOrder> placeBookOrder(
            @PathVariable int customerId,
            @PathVariable int bookId,
            @RequestBody BookOrderRequest bookOrderRequest) {
        BookOrder bookOrder = bookService.placeBookOrder(customerId, bookId, bookOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookOrder);
    }


    // Endpoint for users to write a review for a book
    @PostMapping("/{customerId}/reviews/{bookId}")
    public ResponseEntity<Review> addReview(
            @PathVariable int customerId,
            @PathVariable int bookId,
            @RequestBody Review review) {
        Review savedReview = reviewService.addReview(customerId, bookId, review);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }
}
