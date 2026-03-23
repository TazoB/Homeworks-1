import com.example.LibraryProject.Database.DAO.BorrowingsDAO;
import com.example.LibraryProject.Model.Borrowing;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BorrowingsDAOTest {

    private BorrowingsDAO dao;

    @Test
    void shouldInsertAndFindById() {
        dao = new BorrowingsDAO();

        Date borrowDate = Date.valueOf(LocalDate.now());

        Borrowing borrowing = new Borrowing("1", 1, borrowDate, null);
        dao.insert(borrowing);

        Borrowing found = dao.findById("1", 1, borrowDate);

        assertNotNull(found);
        assertEquals("1", found.getBookCode());
        assertEquals(1, found.getMemberId());
        assertEquals(borrowDate, found.getBorrowDate());
        assertNull(found.getReturnDate());
    }

    @Test
    void shouldReturnNullWhenBorrowingNotFound() {
        dao = new BorrowingsDAO();

        Date borrowDate = Date.valueOf(LocalDate.now());

        Borrowing found = dao.findById("999", 99, borrowDate);

        assertNull(found);
    }

    @Test
    void shouldReturnAllBorrowings() {
        dao = new BorrowingsDAO();

        Date borrowDate = Date.valueOf(LocalDate.now());

        dao.insert(new Borrowing("1", 1, borrowDate, null));
        dao.insert(new Borrowing("2", 2, borrowDate, null));

        List<Borrowing> borrowings = dao.findAll();

        assertEquals(2, borrowings.size());
    }

    @Test
    void shouldUpdateReturnDate() {
        dao = new BorrowingsDAO();

        Date borrowDate = Date.valueOf(LocalDate.now());

        dao.insert(new Borrowing("1", 1, borrowDate, null));

        dao.update("1");

        Borrowing updated = dao.findById("1", 1, borrowDate);

        assertNotNull(updated);
        assertNotNull(updated.getReturnDate());
    }
}