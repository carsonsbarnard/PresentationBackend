package com.bookstorestaticwebsite.StaticBookStoreWebsite.review;

import com.bookstorestaticwebsite.StaticBookStoreWebsite.book.Book;
import com.bookstorestaticwebsite.StaticBookStoreWebsite.book.BookRepository;
import com.bookstorestaticwebsite.StaticBookStoreWebsite.customer.Customer;
import com.bookstorestaticwebsite.StaticBookStoreWebsite.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    /**
     * Adds a review for a specific book from a specific customer.
     *
     * @param customerId the ID of the customer adding the review
     * @param bookId the ID of the book being reviewed
     * @param review the review to be added
     * @return the saved Review
     */
    @Transactional
    public Review addReview(int customerId, int bookId, Review review) {
        // Retrieve customer and book entities
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        // Set the associations in the Review entity
        review.setCustomer(customer);
        review.setBook(book);

        // Save and return the review
        return reviewRepository.save(review);
    }
}
