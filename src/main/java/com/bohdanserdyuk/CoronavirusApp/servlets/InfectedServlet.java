package com.bohdanserdyuk.CoronavirusApp.servlets;

import com.bohdanserdyuk.CoronavirusApp.model.ejb.DoctorDao;
import com.bohdanserdyuk.CoronavirusApp.model.ejb.impl.InfectedEjb;
import com.bohdanserdyuk.CoronavirusApp.model.entities.Doctor;
import com.bohdanserdyuk.CoronavirusApp.model.entities.Infected;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "InfectedServlet", urlPatterns = "/infected")
public class InfectedServlet extends HttpServlet {

    @EJB
    InfectedEjb infectedEjb;
    @EJB
    DoctorDao doctorDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isDeadStr = request.getParameter("isDead");

        Doctor doctor = doctorDao.getDoctorByName(request.getParameter("name"));
        List<Infected> infectedList = infectedEjb.getAllInfected();

        if (isDeadStr != null) {
            updateDoctorsPractice(isDeadStr, doctor);
            doctor = doctorDao.getDoctorById(doctor.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("infected.jsp");
        request.setAttribute("doctor", doctor);
        request.setAttribute("infectedList", infectedList);
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void updateDoctorsPractice(String isDeadStr, Doctor doctor) {
        int c;
        if (isDeadStr.equals("true")) {
            c = doctor.getDeaths();
            doctor.setDeaths(++c);
        } else {
            c = doctor.getCured();
            doctor.setCured(++c);
        }
        doctorDao.updateDoctor(doctor);
    }
}
