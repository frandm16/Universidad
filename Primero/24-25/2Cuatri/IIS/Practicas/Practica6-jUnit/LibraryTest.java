package com.example.library;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class LibraryTest {
        private Library library;

        @BeforeEach
        public void setUp() {
            library = new Library();
        }

        @AfterEach
        public void tearDown() {
            library = null;
        }

        @Test
        public void shouldConstructorCreateAnEmptyLibrary() {
            assertEquals(0, library.countBooks(), "La biblioteca debe estar vacía al inicializarse.");
        }

        @Test
        public void shouldALibraryContainOneBookWhenTheFirstBookIsAdded() {
            Book book = new Book("The Hobbit", "J.R.R. Tolkien", 1937);
            library.addBook(book);
            assertEquals(1, library.countBooks(), "La biblioteca debe contener exactamente un libro.");
        }

        @Test
        public void shouldFindBooksByAuthorsReturnANonEmptyListWhenABookOfTheAuthorIsInTheLibrary() {
            Book book = new Book("The Hobbit", "J.R.R. Tolkien", 1937);
            library.addBook(book);
            List<Book> booksByTolkien = library.findBooksByAuthor("J.R.R. Tolkien");

            assertFalse(booksByTolkien.isEmpty(), "La lista de libros del autor no debe estar vacía.");
        }

        @Test
        public void shouldALibraryBecomeEmptyWhenTheLastBookIsRemoved() {
            Book book = new Book("The Hobbit", "J.R.R. Tolkien", 1937);
            library.addBook(book);
            library.removeBook("The Hobbit", "J.R.R. Tolkien");

            assertEquals(0, library.countBooks(), "La biblioteca debe quedar vacía tras eliminar el único libro.");
        }

        @Test
        public void shouldRemoveBookDecreaseTheNumberOfBooks() {

            Book book1 = new Book("The Hobbit", "J.R.R. Tolkien", 1937);
            Book book2 = new Book("1984", "George Orwell", 1949);
            library.addBook(book1);
            library.addBook(book2);
            library.removeBook("1984", "George Orwell");

            assertEquals(1, library.countBooks(), "Debe quedar un solo libro después de eliminar uno.");
        }

        @Test
        public void shouldRemoveBookRaiseAnExceptionIfTheBookDoesNotExist() {

            Book book = new Book("The Hobbit", "J.R.R. Tolkien", 1937);
            library.addBook(book);


            Exception exception = assertThrows(BookIsNotInLibraryException.class, () -> {
                library.removeBook("1984", "George Orwell");
            });

            assertEquals("The book 1984, by George Orwell is not in the library", exception.getMessage());

        }
}
