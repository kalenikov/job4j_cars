package ru.job4j.cars.web;

import org.apache.commons.io.FilenameUtils;
import ru.job4j.cars.model.Image;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.PostRepository;
import ru.job4j.cars.repository.PostRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@WebServlet(urlPatterns = "/post.do")
@MultipartConfig(
        location = "\\job4j_cars\\images\\",
        fileSizeThreshold = 1024 * 1024 * 10)
public class PostController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostRepositoryImpl repo = PostRepositoryImpl.getInstance();
        String id = req.getParameter("id");
        User user = (User) req.getSession().getAttribute("user");
        Post post = new Post();
        String path;
        if (id == null) {
            path = "/WEB-INF/jsp/postForm.jsp";
        } else {
            post = repo.get(Integer.parseInt(id));
            if (post.isOwner(user)) {
                path = "/WEB-INF/jsp/postForm.jsp";
            } else {
                path = "/WEB-INF/jsp/postView.jsp";
            }
        }
        if (id == null || post.isOwner(user)) {
            req.setAttribute("brands", repo.getAllCarBrands());
            req.setAttribute("bodies", repo.getAllCarBodyTypes());
        }
        req.setAttribute("post", post);
        req.getRequestDispatcher(path).forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String description = req.getParameter("description");
        User user = (User) req.getSession().getAttribute("user");
        String brandId = req.getParameter("brand-id");
        String bodyId = req.getParameter("body-id");
        boolean sold = req.getParameter("sold") != null;
        int id = Integer.parseInt(req.getParameter("id"));
        Set<Image> images = new HashSet<>();

        List<Part> parts = req.getParts().stream()
                .filter(part -> "file".equals(part.getName()) && part.getSize() > 0)
                .collect(Collectors.toList());

        for (Part part : parts) {
            String name = getRandomFileName(part.getSubmittedFileName());
            part.write(name);
            images.add(new Image(name));
        }

        PostRepository repo = PostRepositoryImpl.getInstance();
        Post post = id == 0 ? new Post() : repo.get(id);
        post.setDescription(description);
        post.setCarBrand(repo.getCarBrand(Integer.parseInt(brandId)));
        post.setCarBodyType(repo.getCarBodyType(Integer.parseInt(bodyId)));
        post.setAuthor(user);
        post.getImages().addAll(images);
        post.setSold(sold);
        PostRepositoryImpl.getInstance().save(post);

        resp.sendRedirect(req.getContextPath());
    }

    private String getRandomFileName(String name) {
        return java.util.UUID.randomUUID() + "." + FilenameUtils.getExtension(name);
    }
}
