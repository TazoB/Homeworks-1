package com.example.LibraryProject.Servlets;
import java.io.*;
import java.util.List;

import com.example.LibraryProject.Database.DAO.BooksDAO;
import com.example.LibraryProject.Database.DatabaseConnectionManager;
import com.example.LibraryProject.Model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletConfig;
import tools.jackson.databind.ObjectMapper;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    private final DatabaseConnectionManager dbcm = DatabaseConnectionManager.getInstance();
    private BooksDAO booksDAO = new BooksDAO();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            resp.setCharacterEncoding("UTF-8");
            List<Book> books = booksDAO.findAll();
            objectMapper.writeValue(out, books);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String title = req.getParameter("title");
        String author = req.getParameter("author");

        if(bookExists(code)) {
            resp.sendError(HttpServletResponse.SC_UNPROCESSABLE_CONTENT, "The book with this code already exists");
        } else {
            booksDAO.insert(new Book(
                    code,
                    title,
                    author
            ));
            resp.sendRedirect("/books.html");
        }
    }

    private boolean bookExists(String code) {
        List<Book> books = booksDAO.findAll();
        for(Book book : books) {
            if(book.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }
}
