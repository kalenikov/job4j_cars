package ru.job4j.cars.web;


import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.UserRepository;
import ru.job4j.cars.repository.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/reg.do")
public class RegController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        UserRepository repo = UserRepositoryImpl.getInstance();
        Optional<User> user = repo.getByEmail(email);
        if (user.isPresent()) {
            req.setAttribute("errorMessage", "this email is already registered");
            req.getRequestDispatcher("/WEB-INF/jsp/reg.jsp").forward(req, resp);
        } else {
            repo.save(new User(req.getParameter("name"), email));
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        }
    }
}
