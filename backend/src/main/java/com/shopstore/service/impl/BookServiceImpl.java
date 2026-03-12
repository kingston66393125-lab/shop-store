package com.shopstore.service.impl;

import com.shopstore.model.Book;
import com.shopstore.repository.BookRepository;
import com.shopstore.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> list() {
        return bookRepository.findAll();
    }
}
