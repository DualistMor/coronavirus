package com.bohdanserdyuk.CoronavirusApp.servlets;

import com.bohdanserdyuk.CoronavirusApp.model.ejb.impl.HashingEjb;
import com.bohdanserdyuk.CoronavirusApp.model.entities.Doctor;
import com.bohdanserdyuk.CoronavirusApp.model.ejb.DoctorDao;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {
    @EJB
    DoctorDao doctorDao;
    @EJB
    HashingEjb hashingEjb;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cured = req.getParameter("cured");
        String deaths = req.getParameter("deaths");
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        Doctor doctor = null;
        try {
            doctor = new Doctor(0, name, hashingEjb.cryptoHash(password), Integer.parseInt(cured), Integer.parseInt(deaths));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (!doctorDao.saveDoctor(doctor)) {
            RequestDispatcher rd = req.getRequestDispatcher("registration.jsp");
            req.setAttribute("regError", true);
            rd.forward(req, resp);
        } else {
            resp.sendRedirect("auth.jsp");
        }
    }
}
