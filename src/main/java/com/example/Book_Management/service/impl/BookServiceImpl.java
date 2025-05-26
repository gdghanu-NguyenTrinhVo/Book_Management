package com.example.Book_Management.service.impl;

import com.example.Book_Management.controller.Book;
import com.example.Book_Management.dto.FilterParamBook;
import com.example.Book_Management.service.BookService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    public static final int LESS_MODE = -1;
    public static final int EQUAL_MODE = 0;
    public static final int GREATER_MODE = 1;

    public static List<Book> books = new ArrayList<>(
            List.of(
                    new Book(1, "title1", "author1", 10, LocalDate.of(2012, 10, 01)),
                    new Book(2, "title2", "author2", 3, LocalDate.of(2005, 05, 22)),
                    new Book(3, "title3", "author3", 8, LocalDate.of(1989, 05, 12))
            )
    );

    //Get all book
    @Override
    public List<Book> getAllBook(){
        return books.stream()
                .collect(Collectors.toList());
    }

    @Override
   //Get book by ID
   public List<Book> getByID(FilterParamBook param){
        Predicate<Book> predicate = book -> true;
        if(param.getId() != null){
            if(param.getIdMode() == EQUAL_MODE){
                predicate = predicate.and(book -> book.getId() == param.getId());
            } else if (param.getIdMode() == GREATER_MODE) {
                predicate = predicate.and(book -> book.getId() >= param.getId());
            }
        }
        return books.stream()
                .filter(predicate)
                .collect(Collectors.toList());
   }

    @Override
    public Long createBook(Book book){
        books.add(book);
        return book.getId();
    }

    @Override
    public Long updateBook(Book book){
        for (Book bookItem : books){
            if(bookItem.getId() == book.getId()){
                bookItem.setId(book.getId());
                bookItem.setTitle(book.getTitle());
                bookItem.setAuthor(book.getAuthor());
                bookItem.setPrice(book.getPrice());
                bookItem.setPublishedDate(book.getPublishedDate());
            }
        }
        return book.getId();
    }

    @Override
    public Long deleteBook(long id){
        for (Book book : books){
            if(book.getId() == id){
                books.remove(book);
            }
        }
        return id;
    }
}
