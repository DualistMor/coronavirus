package com.bohdanserdyuk.CoronavirusApp.servlets;

import com.bohdanserdyuk.CoronavirusApp.model.ejb.DoctorDao;
import com.bohdanserdyuk.CoronavirusApp.model.ejb.impl.InfectedEjb;
import com.bohdanserdyuk.CoronavirusApp.model.ejb.impl.RecoveryEjb;
import com.bohdanserdyuk.CoronavirusApp.model.ejb.impl.TreatmentEjb;
import com.bohdanserdyuk.CoronavirusApp.model.entities.Infected;
import com.bohdanserdyuk.CoronavirusApp.model.entities.Treatment;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.bohdanserdyuk.CoronavirusApp.model.ejb.impl.RecoveryEjb.LifeState.DEAD;

@WebServlet("/treatment")
public class TreatmentServlet extends HttpServlet {
    @EJB
    TreatmentEjb treatmentEjb;
    @EJB
    RecoveryEjb recoveryEjb;
    @EJB
    DoctorDao doctorDao;
    @EJB
    InfectedEjb infectedEjb;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String treatmentIdStr = req.getParameter("treatmentId");
        Treatment treatment;
        Infected infected;
        Integer doctorId;
        Integer infectedId;

        if (treatmentIdStr != null) {
            Integer treatmentId = Integer.parseInt(treatmentIdStr);
            treatment = treatmentEjb.getTreatmentById(treatmentId);
            doctorId = treatment.getDoctorId();
            infectedId = treatment.getInfectedId();
            infected = infectedEjb.getInfectedById(treatment.getInfectedId());
        }
        else {
            doctorId = Integer.parseInt(req.getParameter("doctorId"));
            infectedId = Integer.parseInt(req.getParameter("infectedId"));
            infected = infectedEjb.getInfectedById(infectedId);
            treatment = treatmentEjb.getTreatmentByDoctorAndInfectedId(doctorId, infectedId);
        }

        if (treatment == null) {
            treatmentEjb.createTreatment(doctorId, infectedId);
            treatment = treatmentEjb.getTreatmentByDoctorAndInfectedId(doctorId, infectedId);
        }
        if (treatment.getVaccine() != null) {
            req.setAttribute("vaccine", treatment.getVaccine());
            RecoveryEjb.LifeState ls = recoveryEjb.getInfectedTreatmentResult(infected, treatment.getVaccine());
            req.setAttribute("isDead", ls == DEAD);
        }
        req.setAttribute("treatment", treatment);
        req.setAttribute("doctor", doctorDao.getDoctorById(doctorId));
        req.setAttribute("infected", infected);

        RequestDispatcher rd = req.getRequestDispatcher("treatment.jsp");
        rd.forward(req, resp);
    }
}
