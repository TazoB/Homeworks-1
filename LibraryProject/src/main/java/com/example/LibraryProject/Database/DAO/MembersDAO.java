package com.example.LibraryProject.Database.DAO;

import com.example.LibraryProject.Database.DatabaseConnectionManager;
import com.example.LibraryProject.Model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembersDAO {
    private static final DatabaseConnectionManager dbcm = DatabaseConnectionManager.getInstance();
    private static final Connection connection;

    static {
        try {
            connection = dbcm.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Member member) {
        String INSERT = "INSERT INTO Members (name, email) VALUES (?, ?);";

        try (PreparedStatement ps = connection.prepareStatement(INSERT)) {
            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Member findById(int id) {
        String FIND_BY_ID = "SELECT * FROM Members WHERE id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return new Member(
                        id,
                        rs.getString(2),
                        rs.getString(3),
                        (rs.getDate(4) == null) ? null : rs.getDate(4)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Member findByEmail(String email) {
        String FIND_BY_EMAIL = "SELECT * FROM Members WHERE email = ?;";

        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_EMAIL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return new Member(
                        rs.getInt(1),
                        email,
                        rs.getString(3),
                        (rs.getDate(4) == null) ? null : rs.getDate(4)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Member> findAll() {
        String FIND_ALL = "SELECT * FROM Members;";
        List<Member> members = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                members.add(new Member(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        (rs.getDate(4) == null) ? null : rs.getDate(4)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return members;
    }
}
