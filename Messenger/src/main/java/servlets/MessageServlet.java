package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Message;
import model.User;
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
        String username = req.getParameter("username");
        String messageText = req.getParameter("message");

        if (username == null || messageText == null || messageText.contains("\n")) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            resp.getWriter().write("Invalid input");
            return;
        }

        try {
            User receiver = service.findByUsername(username);

            if (receiver == null) {
                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                resp.getWriter().write("User not found");
                return;
            }

            User sender = service.findByUsername("admin");


            if (sender == null) {
                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                resp.getWriter().write("Sender not found");
                return;
            }

            Message message = new Message();
            message.setSender(sender);
            message.setReceiver(receiver);
            message.setContent(messageText);

            service.saveMessage(message);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Message sent");

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error");
        }
    }
}
