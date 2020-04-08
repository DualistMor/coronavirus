package com.bohdanserdyuk.CoronavirusApp.servlets;

import com.bohdanserdyuk.CoronavirusApp.model.ejb.impl.TreatmentEjb;
import com.bohdanserdyuk.CoronavirusApp.model.ejb.impl.VaccineEjb;
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
    @EJB
    VaccineEjb vaccineEjb;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String treatmentId = req.getParameter("treatmentId");
        RequestDispatcher rd = req.getRequestDispatcher("editTreatment.jsp");
        req.setAttribute("treatmentId", treatmentId);
        req.setAttribute("vaccines", vaccineEjb.getAllVaccines());
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int treatmentId = Integer.parseInt(req.getParameter("treatmentId"));
        int vaccineId = Integer.parseInt(req.getParameter("vacId"));
        treatmentEjb.updateTreatmentVaccine(treatmentId, vaccineId);
        RequestDispatcher rd = req.getRequestDispatcher("treatment");
        rd.forward(req, resp);
    }
}