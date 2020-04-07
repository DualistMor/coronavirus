package com.bohdanserdyuk.CoronavirusApp.servlets;

import com.bohdanserdyuk.CoronavirusApp.model.ejb.impl.TreatmentEjb;
import com.bohdanserdyuk.CoronavirusApp.model.entities.Treatment;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/treatment")
public class TreatmentServlet extends HttpServlet {
    @EJB
    TreatmentEjb treatmentEjb;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd;
        int doctorId = Integer.parseInt(req.getParameter("doctorId"));
        int infectedId = Integer.parseInt(req.getParameter("infectedId"));
        Treatment treatment = treatmentEjb.getTreatmentByDoctorAndInfectedId(doctorId, infectedId);
        if (treatment == null) {
            treatmentEjb.createTreatment(doctorId, infectedId);
            treatment = treatmentEjb.getTreatmentByDoctorAndInfectedId(doctorId, infectedId);
        }
        rd = req.getRequestDispatcher("treatment.jsp");
        req.setAttribute("treatment", treatment);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("vaccines");
    }
}
