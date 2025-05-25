package com.example.Book_Management.service;

import com.example.Book_Management.controller.Book;
import com.example.Book_Management.dto.FilterParamBook;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface BookService {
    List<Book> getBook(FilterParamBook filterParamBook);
    Long createBook(Book book);
    Long updateBook(Book book);
    Long deleteBook(long id);

}
