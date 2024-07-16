package com.anthonydelacruz.listadodelibros.Controlador;

import com.anthonydelacruz.listadodelibros.model.Book;
import com.anthonydelacruz.listadodelibros.model.Author;
import com.anthonydelacruz.listadodelibros.service.BookService;
import com.anthonydelacruz.listadodelibros.service.ServiciosDeAutores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Consola{

    @Autowired
    private BookService bookService;

    @Autowired
    private ServiciosDeAutores authorService;

    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("1. Listar libros");
            System.out.println("2. Listar autores");
            System.out.println("3. Listar autores vivos en un año determinado");
            System.out.println("4. Listar libros por idioma");
            System.out.println("5. Añadir un nuevo libro");
            System.out.println("6. Traducir libro");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consumir la nueva línea

            switch (choice) {
                case 1:
                    listBooks();
                    break;
                case 2:
                    listAuthors();
                    break;
                case 3:
                    listAuthorsAliveInYear();
                    break;
                case 4:
                    listBooksByLanguage();
                    break;
                case 5:
                    addNewBook();
                    break;
                case 6:
                    translateBook();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void listBooks() {
        List<Book> books = bookService.getAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void listAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        for (Author author : authors) {
            System.out.println(author);
        }
    }

    private void listAuthorsAliveInYear() {
        System.out.print("Ingrese el año: ");
        int year = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea

        List<Author> authors = authorService.getAuthorsAliveInYear(year);
        for (Author author : authors) {
            System.out.println(author);
        }
    }

    private void listBooksByLanguage() {
        System.out.print("Ingrese el código del idioma (ES, EN, FR, PT): ");
        String language = scanner.nextLine().toUpperCase();

        List<Book> books = bookService.getBooksByLanguage(language);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void addNewBook() {
        System.out.print("Ingrese el título del libro: ");
        String title = scanner.nextLine();
        System.out.print("Ingrese el autor del libro: ");
        String author = scanner.nextLine();
        System.out.print("Ingrese el idioma del libro (ES, EN, FR, PT): ");
        String language = scanner.nextLine().toUpperCase();

        try {
            Book book = new Book(title, author, language);
            bookService.addBook(book);
            System.out.println("Libro añadido con éxito.");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void translateBook() {
        System.out.print("Ingrese el título del libro a traducir: ");
        String title = scanner.nextLine();
        Book book = bookService.getBookByTitle(title);

        if (book == null) {
            System.out.println("El libro no fue encontrado.");
            return;
        }

        // Implementar la lógica de traducción usando ChatGPTAPI aquí
        // Por ahora, solo mostraremos un mensaje ficticio de traducción.
        System.out.println("Traduciendo el libro: " + book.getTitle());
        // Aquí iría la llamada a la API de ChatGPT para la traducción
        System.out.println("El libro ha sido traducido (ficticiamente) exitosamente.");
    }
}