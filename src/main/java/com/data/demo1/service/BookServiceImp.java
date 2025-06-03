package com.data.demo1.service;


import com.data.demo1.entity.Book;
import com.data.demo1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImp implements BookService{
    @Autowired
    private BookRepository bookRepo;

    @Override
    public List<Book> getAll() {
        return bookRepo.getAll();
    }

    @Override
    public void delete(int id) {
        bookRepo.delete(id);
    }
}
