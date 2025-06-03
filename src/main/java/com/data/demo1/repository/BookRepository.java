package com.data.demo1.repository;



import com.data.demo1.entity.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAll();

    void delete(int id);
}
