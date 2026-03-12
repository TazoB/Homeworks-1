package com.example.LibraryProject.Servlets;

import com.example.LibraryProject.Database.DAO.MembersDAO;
import com.example.LibraryProject.Database.DatabaseConnectionManager;
import com.example.LibraryProject.Model.Member;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MemberServlet extends HttpServlet {
    private final DatabaseConnectionManager dbcm = DatabaseConnectionManager.getInstance();
    private MembersDAO membersDAO = new MembersDAO();

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String pathInfo = req.getPathInfo();

        if(pathInfo == null || pathInfo.equals("/")) {
            List<Member> members = membersDAO.findAll();
            out.println("<h1>Members</h1>");
            out.println("<ul>");

            for(Member m : members){
                out.println("<li>" + m.getId() + ": " + m.getName() + "</li>");
            }
            out.println("</ul>");
        } else {
            int id = Integer.parseInt(pathInfo.substring(1));
            Member member = membersDAO.findById(id);

            if(member == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("<p>Member Not Found</p>");
            } else {
                out.println("<h1>Member:</h1>");
                out.println("<ul>");
                out.println("<li>" + member.getId() + ": " + member.getName() + "</li>");
                out.println("</ul>");
            }
        }
        out.println("<br>");
        out.println("""
            <h2>Add Member</h2>
            <form method="POST" action="/members">
            Name: <input name="name"><br>
            Email: <input name="email"><br>
            <button type="submit">Add</button>
            </form>
        """);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        membersDAO.insert(new Member(name, email));
        resp.sendRedirect("/members");
    }
}
