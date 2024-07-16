package com.anthonydelacruz.listadodelibros.repositorio;

import com.anthonydelacruz.listadodelibros.model.Autor;
import com.anthonydelacruz.listadodelibros.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByLanguage(String language);
    Book findByTitle(String title);
}

package com.example.bookdatabase.repository;

import com.example.bookdatabase.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Autor, Long> {
    List<Author> findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(int birthYear, int deathYear);
}
