import com.example.LibraryProject.Database.DAO.MembersDAO;
import com.example.LibraryProject.Model.Member;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MembersDAOTest {

    private MembersDAO dao;

    @Test
    void shouldInsertAndFindById() {
        dao = new MembersDAO();

        Member member = new Member(0, "John Doe", "john@example.com", null);
        dao.insert(member);
        Member found = dao.findById(1);

        assertNotNull(found);
        assertEquals(1, found.getId());
        assertEquals("John Doe", found.getName());
        assertEquals("john@example.com", found.getEmail());
    }

    @Test
    void shouldReturnNullWhenMemberNotFoundById() {
        dao = new MembersDAO();

        Member found = dao.findById(999);

        assertNull(found);
    }

    @Test
    void shouldFindByEmail() {
        dao = new MembersDAO();

        dao.insert(new Member(0, "Alice", "alice@example.com", null));

        Member found = dao.findByEmail("alice@example.com");

        assertNotNull(found);
        assertEquals("alice@example.com", found.getEmail());
    }

    @Test
    void shouldReturnNullWhenEmailNotFound() {
        dao = new MembersDAO();

        Member found = dao.findByEmail("notfound@example.com");

        assertNull(found);
    }

    @Test
    void shouldReturnAllMembers() {
        dao = new MembersDAO();

        dao.insert(new Member(0, "User1", "u1@mail.com", null));
        dao.insert(new Member(0, "User2", "u2@mail.com", null));

        List<Member> members = dao.findAll();

        assertEquals(2, members.size());
    }
}