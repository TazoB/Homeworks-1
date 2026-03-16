package com.example.LibraryProject.Servlets;

import com.example.LibraryProject.Database.DAO.MembersDAO;
import com.example.LibraryProject.Database.DatabaseConnectionManager;
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

public class MemberServlet extends HttpServlet {
    private final DatabaseConnectionManager dbcm = DatabaseConnectionManager.getInstance();
    private MembersDAO membersDAO = new MembersDAO();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String pathInfo = req.getPathInfo();

        if(pathInfo == null || pathInfo.equals("/")) {
            List<Member> members = membersDAO.findAll();
            objectMapper.writeValue(out, members);
        } else {
            int id = Integer.parseInt(pathInfo.substring(1));
            Member member = membersDAO.findById(id);

            if(member == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } else {
                objectMapper.writeValue(out, member);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        membersDAO.insert(new Member(name, email));
        resp.sendRedirect("/members");
    }
}
