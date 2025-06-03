package com.example.Book_Management.service;

import com.example.Book_Management.entity.BookEntity;
import com.example.Book_Management.dto.FilterParamBook;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface BookService {
    List<BookEntity> getAllBook();
    Long createBook(BookEntity bookEntity);
    Long updateBook(long id, BookEntity bookEntity);
    Long deleteBook(long id);
    List<BookEntity> getByID(FilterParamBook filterParamBook);
}
