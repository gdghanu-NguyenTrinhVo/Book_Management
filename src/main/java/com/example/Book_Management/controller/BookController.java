package com.example.Book_Management.controller;

import com.example.Book_Management.dto.FilterParamBook;
import com.example.Book_Management.entity.BookEntity;
import com.example.Book_Management.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    @Autowired
    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getByID(@PathVariable("id") long id,
                                     @RequestParam(value = "idMode", required = false) Long idMode
    ){
        FilterParamBook filterParamBook = new FilterParamBook();
        filterParamBook.setId(id);
        filterParamBook.setIdMode(idMode);
        return ResponseEntity.ok(bookService.getByID(filterParamBook));
    }

    @GetMapping
    public ResponseEntity<?> getAllBook(){
      return ResponseEntity.ok(bookService.getAllBook());
    }

    @PostMapping
    public ResponseEntity<?> createBook(@Valid @RequestBody BookEntity bookEntity){
        return ResponseEntity.ok(bookService.createBook(bookEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable("id") long id,@RequestBody BookEntity bookEntity){
        return ResponseEntity.ok(bookService.updateBook(id, bookEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") long id){
        return ResponseEntity.ok(bookService.deleteBook(id));
    }
}
