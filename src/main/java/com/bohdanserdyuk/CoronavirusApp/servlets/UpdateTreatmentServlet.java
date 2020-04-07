package com.bohdanserdyuk.CoronavirusApp.servlets;

import com.bohdanserdyuk.CoronavirusApp.data.ejb.impl.TreatmentEjb;
import com.bohdanserdyuk.CoronavirusApp.data.models.Treatment;
import com.bohdanserdyuk.CoronavirusApp.data.models.Vaccine;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateTreatment")
public class UpdateTreatmentServlet extends HttpServlet {
    @EJB
    TreatmentEjb treatmentEjb;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int treatmentId = Integer.parseInt(req.getParameter("id"));
        RequestDispatcher rd = req.getRequestDispatcher("updateTreatment.jsp");
        Treatment treatment = treatmentEjb.getInfectedTreatmentById(treatmentId);
        req.setAttribute("treatment", treatment);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int treatmentId = Integer.parseInt(req.getParameter("id"));
        String vaccineCode = req.getParameter("vaccineCode");

        treatmentEjb.updateTreatmentVaccine(vaccineCode, treatmentId);
        resp.sendRedirect("vaccines");
    }
}