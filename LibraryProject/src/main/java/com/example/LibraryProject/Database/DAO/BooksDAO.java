package com.example.LibraryProject.Database.DAO;

public class BooksDAO {
    private static final DatabaseConnectionManager dbcm = DatabaseConnectionManager.getInstance();
    private static final Connection connection;

    static {
        try {
            connection = dbcm.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Book book) {
        String INSERT = "INSERT INTO Books (code, title, author) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setString(1, book.getCode());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Book findByCode(String code) {
        String FIND_BY_ID = "SELECT * FROM Books WHERE code = '?';";

        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return new Book(
                        code,
                        rs.getString(2),
                        rs.getString(3)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String FIND_ALL = "SELECT * FROM Books;";

        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                books.add(new Book(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

//    public void delete() {
//
//    }

//    public void update() {
//
//    }
}
