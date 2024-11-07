package com.bookstorestaticwebsite.StaticBookStoreWebsite.book;

import com.bookstorestaticwebsite.StaticBookStoreWebsite.admin.User;
import com.bookstorestaticwebsite.StaticBookStoreWebsite.customer.Customer;
import com.bookstorestaticwebsite.StaticBookStoreWebsite.customer.CustomerRepository;
import com.bookstorestaticwebsite.StaticBookStoreWebsite.order.BookOrder;
import com.bookstorestaticwebsite.StaticBookStoreWebsite.order.BookOrderRepository;
import com.bookstorestaticwebsite.StaticBookStoreWebsite.order.BookOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookOrderRepository bookOrderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public BookOrder placeBookOrder(int customerId, int bookId, BookOrderRequest bookOrderRequest) {
        // Fetch Customer by ID
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        // Fetch Book by ID
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        // Create BookOrder with the details from the request
        BookOrder bookOrder = new BookOrder(
                customer,
                book,
                bookOrderRequest.getSubtotal(),
                bookOrderRequest.getShippingFee(),
                bookOrderRequest.getTax(),
                bookOrderRequest.getTotal(),
                bookOrderRequest.getPaymentMethod(),
                "Pending" // Set default status as "Pending"
        );

        // Save the BookOrder and return it
        return bookOrderRepository.save(bookOrder);
    }




    public void createNewBook(Book book){
        //check if this title already exist or not
        if(checkTitleExist(book)){
            System.out.println("Already exist");
            return;
        }
        String imagePath = "/images/books/"+book.getImagePath();
        book = new Book(book.getTitle(), book.getAuthor(), book.getDescription(), book.getIsbn(), book.getPrice(),book.getPublishDate(), imagePath,null, book.getCategory());
        bookRepository.save(book);
    }

    public Book getBookById(int id){
        return bookRepository.findById(id).orElse(null);
    }

    public void updateBook(Book book, int id){
        Book existing = getBookById(id);
        String imagePath = "/images/books/"+book.getImagePath();
           existing.setTitle(book.getTitle());
           existing.setAuthor(book.getAuthor());
           existing.setCategory(book.getCategory());
           existing.setDescription(book.getDescription());
           existing.setIsbn(book.getIsbn());
           existing.setImagePath(imagePath);
           existing.setPrice(book.getPrice());
           existing.setPublishDate(book.getPublishDate());
           existing.setLastUpdateDate(book.getLastUpdateDate());
        bookRepository.save(existing);

    }

    public void deleteBookById(int id){
        bookRepository.deleteById(id);
    }


    public boolean checkTitleExist(Book book){
        boolean existed = false;
        List<Book> list_books = getAllBooks();
        for (Book b : list_books){
            if(book.getTitle().equals(b.getTitle())){
                existed = true;
                break;
            }
        }
        return existed;
    }
    public String getImageData(byte[] image, Book book){
        return Base64.getMimeEncoder().encodeToString(image);
    }
}
