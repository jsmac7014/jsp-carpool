package com.jungwoo.tukoreacarpool.Controller;

import java.awt.*;
import java.io.*;

import com.jungwoo.tukoreacarpool.DAO.UserDAO;
import com.jungwoo.tukoreacarpool.DO.UserDO;
import com.sun.net.httpserver.Request;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/sign-in")
public class SignInController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("title", "로그인");
        req.setAttribute("content", "/sign-in.jsp");

        RequestDispatcher rd = req.getRequestDispatcher("/layouts/main_layout.jsp");
        rd.forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        handleLogin(req, res);
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserDO user = new UserDO();
        user.setUsername(username);
        user.setPassword(password);

        boolean isValid = userDAO.validateUser(user);

        if (isValid) {
            HttpSession session = req.getSession();
            session.setAttribute("username", user.getUsername());
            res.sendRedirect("/");
        } else {
            req.setAttribute("error", "유효하지 않은 정보");
            req.setAttribute("content", "/sign-in.jsp");
            RequestDispatcher rd = req.getRequestDispatcher("/layouts/main_layout.jsp");
            rd.forward(req, res);
        }
    }

}