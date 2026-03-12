package com.example.LibraryProject.Database;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance;
    private static final String URL = "jdbc:postgresql://localhost:5432/Library Database";
    private static final String USER = "postgres";
    private static final String PASSWORD = "mskhj";


    public DatabaseConnectionManager() {
    }

    public static DatabaseConnectionManager getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    public void initialize() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            File schemaFile = new File("src/main/resources/Schema.sql");
            String schemaText = getSchema(schemaFile);
            statement.executeUpdate(schemaText);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getSchema(File file) {
        try (BufferedReader in = new BufferedReader(
                new FileReader(file)
        )) {
            StringBuilder text = new StringBuilder();
            while(in.ready()) {
                text.append(in.readLine());
                text.append("\n");
            }
            return text.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
