package com.anthonydelacruz.listadodelibros.service;

import com.anthonydelacruz.listadodelibros.model.Book;
import com.anthonydelacruz.listadodelibros.repositorio.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiciosDeLibros{

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> getBooksByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }

    public void addBook(Book book) {
        if (bookRepository.findByTitle(book.getTitle()) == null) {
            bookRepository.save(book);
        } else {
            throw new RuntimeException("El libro ya está registrado en la base de datos.");
        }
    }
}
