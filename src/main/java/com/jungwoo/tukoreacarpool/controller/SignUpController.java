package com.jungwoo.tukoreacarpool.controller;

import java.io.*;
import java.util.regex.Pattern;

import com.jungwoo.tukoreacarpool.dao.UserDAO;
import com.jungwoo.tukoreacarpool.dataobject.UserDO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/sign-up")
public class SignUpController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init(ServletConfig config) throws ServletException {
        userDAO = new UserDAO();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 이미 로그인되어 있는 경우 홈으로 리다이렉션
        HttpSession session = req.getSession();
        if(session != null && session.getAttribute("username") != null) {
            res.sendRedirect("/");
            return;
        }

        req.setCharacterEncoding("UTF-8");
        req.setAttribute("title", "회원가입");
        req.setAttribute("content", "/sign-up.jsp");

        RequestDispatcher rd = req.getRequestDispatcher("/layouts/main_layout.jsp");
        rd.forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        handleCreateAccount(req, res);
    }


    private void handleCreateAccount(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        if(username == null || password == null || email == null) {
            req.setAttribute("error", "입력해라");
            req.setAttribute("content", "/sign-up.jsp");
            RequestDispatcher rd = req.getRequestDispatcher("/layouts/main_layout.jsp");
            rd.forward(req, res);
            return;
        }

//      email should only be from @tukorea.ac.kr
        String emailPattern = "^[a-zA-Z0-9._%+-]+@tukorea\\.ac\\.kr$";
        if(!Pattern.matches(emailPattern, email)) {
            req.setAttribute("error", "이메일이 @tukorea.ac.kr로 끝나는지 확인해주세요.");
            req.setAttribute("content", "/sign-up.jsp");
            RequestDispatcher rd = req.getRequestDispatcher("/layouts/main_layout.jsp");
            rd.forward(req, res);
            return;
        }
//        TODO Check if email exist

        UserDO user = new UserDO();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setVerified(false);

        userDAO.createUser(user);

        res.sendRedirect(req.getContextPath() + "/sign-in");
    }
}