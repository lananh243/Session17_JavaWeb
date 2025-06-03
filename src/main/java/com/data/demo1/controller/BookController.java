package com.data.demo1.controller;


import com.data.demo1.entity.Book;
import com.data.demo1.service.BookService;
import com.data.demo1.service.BookServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String getAll(Model model) {
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);

        return "book_list";
    }

    @GetMapping("book-delete/{id}")
    public String delete(@PathVariable int id) {
        bookService.delete(id);

        return "redirect:/books";
    }


}
