package com.jungwoo.tukoreacarpool.controller;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Date;

import com.jungwoo.tukoreacarpool.dao.PartyDAO;
import com.jungwoo.tukoreacarpool.dao.PartyHasUserDAO;
import com.jungwoo.tukoreacarpool.dao.UserDAO;
import com.jungwoo.tukoreacarpool.dataobject.PartyDO;
import com.jungwoo.tukoreacarpool.dataobject.PartyHasUserDO;
import com.jungwoo.tukoreacarpool.dataobject.UserDO;
import com.jungwoo.tukoreacarpool.types.PartyUserType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/party")
public class PartyController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PartyDAO partyDAO;
    private UserDAO userDAO;
    private PartyHasUserDAO partyHasUserDAO;

    public void init(ServletConfig config) throws ServletException {
        partyDAO = new PartyDAO();
        userDAO = new UserDAO();
        partyHasUserDAO = new PartyHasUserDAO();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("title", "파티 모집하기");
        req.setAttribute("content", "/create-party.jsp");
        RequestDispatcher rd = req.getRequestDispatcher("/layouts/main_layout.jsp");
        rd.forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String type = req.getParameter("type");
        String fromName = req.getParameter("start-point-name");
        String toName = req.getParameter("release-point-name");
        String fromAddress = req.getParameter("start-point-address");
        String toAddress = req.getParameter("release-point-address");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String datetime = req.getParameter("datetime");
        int size = Integer.parseInt(req.getParameter("size"));
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();



        PartyDO partyDO = new PartyDO(title, description, fromName, toName, fromAddress, toAddress, size, type, createdAt, createdAt, updatedAt);
        int partyId = partyDAO.createParty(partyDO);

        HttpSession session = req.getSession();
        String userEmail = (String) session.getAttribute("email");

        // Find userId using email
        UserDO user = userDAO.getUserByEmail(userEmail);

        PartyHasUserDO partyHasUserDO = new PartyHasUserDO(user.getId(), partyId, PartyUserType.PARTY_OWNER.getValue(), LocalDateTime.now());
        partyHasUserDAO.create(partyHasUserDO);

        res.sendRedirect(req.getContextPath() + "/");
    }

}