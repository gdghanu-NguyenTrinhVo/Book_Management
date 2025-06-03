package com.example.Book_Management.service.impl;

import com.example.Book_Management.entity.BookEntity;
import com.example.Book_Management.dto.FilterParamBook;
import com.example.Book_Management.reposity.BookRepository;
import com.example.Book_Management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    public static final long LESS_MODE = -1;
    public static final long GREATER_MODE = 1;

    @Autowired
    private BookRepository bookRepository;

    //Get all book
    @Override
    public List<BookEntity> getAllBook(){
        return bookRepository.findAll();
    }

    @Override
   //Get book by id
   public List<BookEntity> getByID(FilterParamBook param){
        if (param.getIdMode() == null){
            return bookRepository.findById(param.getId())
                    .stream().collect(Collectors.toList());
        } else {
            //Get book with id number less than or greater than specified  id
            Predicate<BookEntity> predicate = bookEntity -> true;
            if(param.getId() != null){
                if(param.getIdMode() == LESS_MODE){
                    predicate = predicate.and(bookEntity -> bookEntity.getId() <= param.getId());
                } else if (param.getIdMode() == GREATER_MODE) {
                    predicate = predicate.and(bookEntity -> bookEntity.getId() >= param.getId());
                }
            }
            return bookRepository.findAll().stream()
                    .filter(predicate)
                    .collect(Collectors.toList());
        }
   }

    @Override
    public Long createBook(BookEntity bookEntity){
        BookEntity savedBookEntity = bookRepository.save(bookEntity);
        return savedBookEntity.getId();
    }

    @Override
    public Long updateBook(long id, BookEntity bookEntity){
        BookEntity bookEntityItem = bookRepository.findById(id)
                .orElse(null);
        bookEntityItem.setId(bookEntity.getId());
        bookEntityItem.setTitle(bookEntity.getTitle());
        bookEntityItem.setAuthor(bookEntity.getAuthor());
        bookEntityItem.setPrice(bookEntity.getPrice());
        bookEntityItem.setPublishedDate(bookEntity.getPublishedDate());
        bookRepository.save(bookEntityItem);
        return bookEntityItem.getId();
    }

    @Override
    public Long deleteBook(long id){
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return id;
        }
        return null;
    }
}
