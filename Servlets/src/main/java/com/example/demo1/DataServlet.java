package com.example.demo1;

import jakarta.servlet.http.*;
import java.io.*;
import java.util.stream.Collectors;

public class DataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {

            String json = DatabaseService.getAllItems()
                    .stream()
                    .map(item -> String.format(
                            "{\"id\":%d,\"name\":\"%s\",\"price\":%.2f,\"quantity\":%d}",
                            item.getId(),
                            item.getName(),
                            item.getPrice(),
                            item.getQuantity()))
                    .collect(Collectors.joining(","));

            out.print("[" + json + "]");
            return;
        }

        int id = Integer.parseInt(pathInfo.substring(1));
        Item item = DatabaseService.getItem(id);

        if (item == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.print("{\"error\":\"Item not found\"}");
            return;
        }

        out.printf("{\"id\":%d,\"name\":\"%s\",\"price\":%.2f,\"quantity\":%d}",
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getQuantity());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String methodOverride = req.getParameter("_method");

        if ("put".equalsIgnoreCase(methodOverride)) {
            handleUpdate(req, resp);
            return;
        }

        if ("delete".equalsIgnoreCase(methodOverride)) {
            handleDelete(req, resp);
            return;
        }

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));

            DatabaseService.addItem(new Item(id, name, price, quantity));

            resp.getWriter().println("Item added successfully.");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Invalid input.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String pathInfo = req.getPathInfo();
        if (pathInfo == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int id = Integer.parseInt(pathInfo.substring(1));

        Double price = req.getParameter("price") != null
                ? Double.parseDouble(req.getParameter("price"))
                : null;

        Integer quantity = req.getParameter("quantity") != null
                ? Integer.parseInt(req.getParameter("quantity"))
                : null;

        DatabaseService.updateItem(id, price, quantity);
        resp.getWriter().println("Item updated successfully.");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String pathInfo = req.getPathInfo();
        if (pathInfo == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int id = Integer.parseInt(pathInfo.substring(1));
        DatabaseService.deleteItem(id);

        resp.getWriter().println("Item deleted successfully.");
    }

    private void handleUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));

            Double price = (req.getParameter("price") != null && !req.getParameter("price").isEmpty())
                    ? Double.parseDouble(req.getParameter("price"))
                    : null;

            Integer quantity = (req.getParameter("quantity") != null && !req.getParameter("quantity").isEmpty())
                    ? Integer.parseInt(req.getParameter("quantity"))
                    : null;

            DatabaseService.updateItem(id, price, quantity);
            resp.getWriter().println("Item updated successfully.");

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Invalid update data.");
        }
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            DatabaseService.deleteItem(id);

            resp.getWriter().println("Item deleted successfully.");

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Invalid delete request.");
        }
    }
}