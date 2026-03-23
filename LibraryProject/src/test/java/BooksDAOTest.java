import com.example.LibraryProject.Database.DAO.BooksDAO;
import com.example.LibraryProject.Model.Book;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BooksDAOTest {
    private BooksDAO dao;

    @Test
    void shouldInsertAndFindByCode() {
        Book book = new Book("1", "Clean Code", "Robert Martin");
        dao = new BooksDAO();
        dao.insert(book);

        Book found = dao.findByCode("1");

        assertNotNull(found);
        assertEquals("1", found.getCode());
        assertEquals("Clean Code", found.getTitle());
        assertEquals("Robert Martin", found.getAuthor());
    }

    @Test
    void shouldReturnNullWhenBookNotFound() {
        dao = new BooksDAO();
        Book found = dao.findByCode("999");
        assertNull(found);
    }

    @Test
    void shouldReturnAllBooks() {
        dao = new BooksDAO();
        dao.insert(new Book("1", "Book1", "Author1"));
        dao.insert(new Book("2", "Book2", "Author2"));

        List<Book> books = dao.findAll();

        assertEquals(2, books.size());
    }
}