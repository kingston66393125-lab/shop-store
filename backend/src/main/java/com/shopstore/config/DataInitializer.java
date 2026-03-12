package com.shopstore.config;

import com.shopstore.model.Book;
import com.shopstore.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initBooks(BookRepository bookRepository) {
        return args -> {
            if (bookRepository.count() == 0) {
                Book book = new Book();
                book.setTitle("Java核心技术");
                book.setAuthor("Cay Horstmann");
                book.setPrice(new BigDecimal("58.00"));
                book.setStock(50);
                bookRepository.save(book);

                Book book2 = new Book();
                book2.setTitle("Spring实战");
                book2.setAuthor("Craig Walls");
                book2.setPrice(new BigDecimal("66.00"));
                book2.setStock(40);
                bookRepository.save(book2);
            }
        };
    }
}
