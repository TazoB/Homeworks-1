package com.example.LibraryProject.Servlets;

import com.example.LibraryProject.Database.DAO.BorrowingsDAO;
import com.example.LibraryProject.Database.DAO.MembersDAO;
import com.example.LibraryProject.Database.DatabaseConnectionManager;
import com.example.LibraryProject.Model.Borrowing;
import com.example.LibraryProject.Model.Member;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/borrows")
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
        List<BorrowingCopy> borrowingCopies = new ArrayList<>();

        for (Borrowing borrowing : borrowings) {
            String name = new MembersDAO().findById(borrowing.getMemberId()).getName();

            borrowingCopies.add(
                    new BorrowingCopy(
                            borrowing.getBookCode(),
                            name,
                            borrowing.getBorrowDate(),
                            borrowing.getReturnDate()
                    )
            );
        }

        objectMapper.writeValue(out, borrowingCopies);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String bookCode = req.getParameter("bookCode");
        if("Add Borrowing".equals(action)) {
            String email = req.getParameter("email");

            Member member = new MembersDAO().findByEmail(email);
            int id = member.getId();

            borrowingsDAO.insert(
                    new Borrowing(bookCode, id)
            );
        } else if ("Return Book".equals(action)) {
            borrowingsDAO.update(bookCode);
        }
        resp.sendRedirect("/borrows.html");
    }
}
