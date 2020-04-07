package com.bohdanserdyuk.CoronavirusApp.servlets;

import com.bohdanserdyuk.CoronavirusApp.data.ejb.impl.TreatmentEjb;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteTreatment")
public class RemoveTreatmentServlet extends HttpServlet {
    @EJB
    TreatmentEjb treatmentEjb;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int treatmentId = Integer.parseInt(req.getParameter("id"));
        treatmentEjb.removeTreatmentById(treatmentId);
        resp.sendRedirect("vaccines");
    }
}
