package com.bohdanserdyuk.CoronavirusApp.servlets;

import com.bohdanserdyuk.CoronavirusApp.model.ejb.impl.HashingEjb;
import com.bohdanserdyuk.CoronavirusApp.model.entities.Doctor;
import com.bohdanserdyuk.CoronavirusApp.model.constants.ParamsConstants;
import com.bohdanserdyuk.CoronavirusApp.model.ejb.DoctorDao;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
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
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        Doctor doctor = doctorDao.getDoctorByName(name);
        try {
            if (doctor != null && doctor.getPassword().equals(hashingEjb.cryptoHash(password))) {
                HttpSession session = req.getSession();
                session.setAttribute(ParamsConstants.DOCTOR, doctor);
                resp.sendRedirect("infected");
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("auth.jsp");
                req.setAttribute("authError", true);
                rd.forward(req, resp);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
