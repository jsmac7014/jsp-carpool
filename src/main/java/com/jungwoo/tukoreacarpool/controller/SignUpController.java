package com.jungwoo.tukoreacarpool.Controller;

import java.io.*;

import com.jungwoo.tukoreacarpool.DAO.UserDAO;
import com.jungwoo.tukoreacarpool.DO.UserDO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/sign-up")
public class SignUpController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init(ServletConfig config) throws ServletException {
        userDAO = new UserDAO();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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

        UserDO user = new UserDO();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setVerified(false);

        userDAO.createUser(user);

        res.sendRedirect("/sign-in");
    }
}