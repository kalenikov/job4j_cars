package ru.job4j.cars.web;

import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/login.do")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        Optional<User> user = UserRepositoryImpl.getInstance().getByEmail(email);
        if (user.isPresent()) {
            req.getSession().setAttribute("user", user.get());
            resp.sendRedirect(req.getContextPath());
        } else {
            req.setAttribute("errorMessage", "Email not found");
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
        }
    }
}
