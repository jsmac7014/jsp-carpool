package com.jungwoo.tukoreacarpool.Controller;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/auth/*")
public class AuthController extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = req.getPathInfo();
        switch (path) {
            case "/sign-in":
                req.setAttribute("title", "로그인");
                req.setAttribute("content", "/sign-in.jsp");

                RequestDispatcher rd = req.getRequestDispatcher("/layouts/main_layout.jsp");
                rd.forward(req, res);

                break;
            case "/sign-up":
                System.out.println("register");
                break;
        }
    }


    private void handleRegister(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

    }

}