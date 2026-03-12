package com.example.LibraryProject.Servlets;
import java.io.*;
import java.util.List;

import com.example.LibraryProject.Database.DAO.BooksDAO;
import com.example.LibraryProject.Database.DatabaseConnectionManager;
import com.example.LibraryProject.Model.Book;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class BookServlet extends HttpServlet {
    private final DatabaseConnectionManager dbcm = DatabaseConnectionManager.getInstance();
    private BooksDAO booksDAO = new BooksDAO();

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            List<Book> books = booksDAO.findAll();
            for (Book book : books) {
                out.println(book + "<br>");
            }
        } else {
            String code = pathInfo.substring(1);
            Book book = booksDAO.findByCode(code);

            if(book == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("<p>Book Not Found</p>");
            } else {
                out.println(book + "<br>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
