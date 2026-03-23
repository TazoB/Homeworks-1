package com.example.LibraryProject.Database.DAO;

import com.example.LibraryProject.Database.DatabaseConnectionManager;
import com.example.LibraryProject.Model.Borrowing;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowingsDAO {
    private static final DatabaseConnectionManager dbcm = DatabaseConnectionManager.getInstance();
    private static final Connection connection;

    static {
        try {
            connection = dbcm.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Borrowing borrowing) {
        String INSERT = "INSERT INTO Borrowings (book_code, member_id) VALUES (?, ?);";

        try (PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setString(1, borrowing.getBookCode());
            ps.setInt(2, borrowing.getMemberId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Borrowing findById(String bookCode, int memberId, Date borrowDate) {
        String FIND_BY_ID = "SELECT * FROM Borrowings WHERE book_code = ? AND member_id = ? AND borrow_date = ?";

        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setString(1, bookCode);
            ps.setInt(2, memberId);
            ps.setDate(3, borrowDate);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return new Borrowing(
                        bookCode,
                        memberId,
                        borrowDate,
                        (rs.getDate(4) == null) ? null : rs.getDate(4)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Borrowing> findAll() {
        String FIND_ALL = "SELECT * FROM Borrowings;";
        List<Borrowing> borrowings = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                borrowings.add(new Borrowing(
                        rs.getString(1),
                        rs.getInt(2),
                        (rs.getDate(3) == null) ? null : rs.getDate(3),
                        (rs.getDate(4) == null) ? null : rs.getDate(4)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return borrowings;
    }

    public void update(String bookCode) {
        String UPDATE = "UPDATE Borrowings SET return_date = ? WHERE book_code = ?";

        try(PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setString(2, bookCode);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
