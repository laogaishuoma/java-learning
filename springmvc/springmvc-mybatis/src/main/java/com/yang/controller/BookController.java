package com.yang.controller;

import com.yang.pojo.Book;
import com.yang.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    @Qualifier("bookServiceImpl")
    BookService bookService;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Book> books = bookService.queryAllBook();
        model.addAttribute("books", books);
        return "test";
    }

    @RequestMapping("/test")
    public String test() {
        return "Hello SSM";
    }
}
