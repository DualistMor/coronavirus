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

@WebServlet("/editTreatment")
public class EditTreatmentServlet extends HttpServlet {
    @EJB
    TreatmentEjb treatmentEjb;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int treatmentId = Integer.parseInt(req.getParameter("id"));
        RequestDispatcher rd = req.getRequestDispatcher("editTreatment.jsp");
        Treatment treatment = treatmentEjb.getTreatmentById(treatmentId);
        req.setAttribute("treatment", treatment);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int treatmentId = Integer.parseInt(req.getParameter("treatmentId"));
        int vaccineId = Integer.parseInt(req.getParameter("vaccineId"));
        treatmentEjb.updateTreatmentVaccine(treatmentId, vaccineId);
        resp.sendRedirect("treatment");
    }
}