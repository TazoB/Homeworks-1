package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MessengerService;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

public class MessageServlet extends HttpServlet {
    private MessengerService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        service = MessengerService.getInstance();
        mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(! service.passwordIsCorrect(username, password)) {
            resp.getWriter().write("Incorrect username or password");
            return;
        }

        mapper.writeValue(resp.getWriter(), service.getMessages(username));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
