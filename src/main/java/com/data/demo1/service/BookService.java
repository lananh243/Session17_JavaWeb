package com.data.demo1.service;



import com.data.demo1.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    void delete(int id);
}
