package com.example.LibraryProject.Servlets;

import com.example.LibraryProject.Database.DAO.BorrowingsDAO;
import com.example.LibraryProject.Database.DAO.MembersDAO;
import com.example.LibraryProject.Database.DatabaseConnectionManager;
import com.example.LibraryProject.Model.Borrowing;
import com.example.LibraryProject.Model.Member;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class BorrowServlet extends HttpServlet {
    private final DatabaseConnectionManager dbcm = DatabaseConnectionManager.getInstance();
    private BorrowingsDAO borrowingsDAO = new BorrowingsDAO();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        List<Borrowing> borrowings = borrowingsDAO.findAll();
        objectMapper.writeValue(out, borrowings);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
