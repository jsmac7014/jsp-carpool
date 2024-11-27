package com.jungwoo.tukoreacarpool.Controller;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/")
public class HomeController extends HttpServlet {
    public void init() {}

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("title", "TU KOREA Carpool");
        req.setAttribute("content", "/home.jsp");
        RequestDispatcher rd = req.getRequestDispatcher("/layouts/main_layout.jsp");
        rd.forward(req, res);
    }
}