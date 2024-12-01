package com.jungwoo.tukoreacarpool.controller;

import java.io.*;

import com.jungwoo.tukoreacarpool.dao.PartyDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/")
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PartyDAO partyDAO;

    public void init() {
        partyDAO = new PartyDAO();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("title", "TU KOREA Carpool");
        req.setAttribute("content", "/home.jsp");
        RequestDispatcher rd = req.getRequestDispatcher("/layouts/main_layout.jsp");
        rd.forward(req, res);
    }
}