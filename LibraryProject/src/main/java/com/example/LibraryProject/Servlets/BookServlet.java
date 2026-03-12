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
            out.println("<h1>Books</h1>");
            out.println("<ul>");

            for(Book b : books){
                out.println("<li>[" + b.getCode() + "] "
                        + b.getTitle() + " - "
                        + b.getAuthor() + "</li>");
            }

            out.println("</ul>");
        } else {
            String code = pathInfo.substring(1);
            Book book = booksDAO.findByCode(code);

            if(book == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("<p>Book Not Found</p>");
            } else {
                out.println("<h1>Book:</h1>");
                out.println("<ul>");
                out.println("<li>[" + book.getCode() + "] "
                        + book.getTitle() + " - "
                        + book.getAuthor() + "</li>");
                out.println("</ul>");
            }
        }
        out.println("<br>");
        out.println("""
            <h2>Add Book</h2>
            <form method="POST" action="/books">
            Code: <input name="code"><br>
            Title: <input name="title"><br>
            Author: <input name="author"><br>
            <button type="submit">Add</button>
            </form>
        """);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String title = req.getParameter("title");
        String author = req.getParameter("author");

        booksDAO.insert(new Book(
                code,
                title,
                author
        ));
        resp.sendRedirect("/books");
    }
}
