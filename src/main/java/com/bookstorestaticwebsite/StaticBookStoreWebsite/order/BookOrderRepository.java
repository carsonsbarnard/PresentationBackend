package com.bookstorestaticwebsite.StaticBookStoreWebsite.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOrderRepository extends JpaRepository<BookOrder, Integer> {
}
