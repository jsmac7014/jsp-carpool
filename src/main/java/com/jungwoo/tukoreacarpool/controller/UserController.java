package com.jungwoo.tukoreacarpool.controller;
import java.io.*;

import com.jungwoo.tukoreacarpool.dao.UserDAO;
import com.jungwoo.tukoreacarpool.dataobject.UserDO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        UserDO user = userDAO.getUserByEmail((String) session.getAttribute("email"));

        req.setCharacterEncoding("UTF-8");
        req.setAttribute("title", "유저정보");
        req.setAttribute("content", "/user.jsp");
        req.setAttribute("user", user);

        RequestDispatcher rd = req.getRequestDispatcher("/layouts/main_layout.jsp");
        rd.forward(req, res);
    }

    public void destroy() {
    }
}