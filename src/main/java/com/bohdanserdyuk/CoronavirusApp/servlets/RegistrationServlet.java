package com.bohdanserdyuk.CoronavirusApp.servlets;

import com.bohdanserdyuk.CoronavirusApp.data.models.Infected;
import com.bohdanserdyuk.CoronavirusApp.data.ejb.InfectedDao;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {
    @EJB
    InfectedDao infectedDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        String name = req.getParameter("name");
        String address = req.getParameter("address");

        Infected infected = new Infected(phone, name, address);

        if (!infectedDao.saveInfected(infected)) {
            RequestDispatcher rd = req.getRequestDispatcher("registration.jsp");
            req.setAttribute("regError", true);
            rd.forward(req, resp);
        } else {
            resp.sendRedirect("auth.jsp");
        }
    }
}
