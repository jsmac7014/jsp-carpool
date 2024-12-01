package com.jungwoo.tukoreacarpool.controller;

import java.io.*;

import com.jungwoo.tukoreacarpool.dao.UserDAO;
import com.jungwoo.tukoreacarpool.dataobject.UserDO;
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
        HttpSession session = req.getSession(false);
        // 이미 로그인되어 있는 경우 홈으로 리다이렉션
        if(session != null && session.getAttribute("username") != null) {
            res.sendRedirect("/");
            return;
        }

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
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        boolean isValid = userDAO.validateUser(email, password);

        if (isValid) {
            HttpSession session = req.getSession();
            session.setAttribute("email", email);
            System.out.println(req.getContextPath());
            res.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("error", "유효하지 않은 정보");
            req.setAttribute("content", "/sign-in.jsp");
            RequestDispatcher rd = req.getRequestDispatcher("/layouts/main_layout.jsp");
            rd.forward(req, res);
        }
    }

}