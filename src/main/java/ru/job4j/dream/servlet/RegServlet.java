package ru.job4j.dream.servlet;

import ru.job4j.dream.model.User;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(req.getContextPath() + "/reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (PsqlStore.instOf().findByEmail(email) != null) {
            req.setAttribute("error", "пользователь с данным email уже зарегистрирован");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        } else {
            PsqlStore.instOf().save(new User(0, name, email, password));
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
}
