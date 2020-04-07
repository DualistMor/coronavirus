package com.bohdanserdyuk.CoronavirusApp.servlets;

import com.bohdanserdyuk.CoronavirusApp.data.models.Infected;
import com.bohdanserdyuk.CoronavirusApp.data.constants.ParamsConstants;
import com.bohdanserdyuk.CoronavirusApp.data.ejb.InfectedDao;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    @EJB
    InfectedDao infectedDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        Infected infected = infectedDao.getInfectedByNumber(phone);
        if (infected != null && infected.getAddress().equals(address)) {
            HttpSession session = req.getSession();
            session.setAttribute(ParamsConstants.USER, infected);
            resp.sendRedirect("coaches");
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("auth.jsp");
            req.setAttribute("authError", true);
            rd.forward(req, resp);
        }
    }
}
