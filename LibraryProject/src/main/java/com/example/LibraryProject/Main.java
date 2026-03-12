package com.example.LibraryProject;

import com.example.LibraryProject.Database.DatabaseConnectionManager;

public class Main {
    public static void main(String[] args) {
        DatabaseConnectionManager dbcm = DatabaseConnectionManager.getInstance();
        dbcm.initialize();
    }
}
