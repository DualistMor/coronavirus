package com.bohdanserdyuk.CoronavirusApp.servlets;

import com.bohdanserdyuk.CoronavirusApp.model.ejb.impl.InfectedEjb;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        List<Infected> infectedList =  infectedEjb.getAllInfected();

        RequestDispatcher rd = request.getRequestDispatcher("infected.jsp");
        request.setAttribute("doctorId", doctorId);
        request.setAttribute("infectedList", infectedList);
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
