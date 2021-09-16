package ru.job4j.cars.web;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(urlPatterns = "/image.do",
        initParams = {
                @WebInitParam(name = "imageDir", value = "c:\\temp\\java\\job4j_cars\\images\\"),
        })
public class ImageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getParameter("path");
        File file = new File(getInitParameter("imageDir") + path);
        if (!file.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        try (FileInputStream stream = new FileInputStream(file)) {
            resp.getOutputStream().write(stream.readAllBytes());
        }
    }
}