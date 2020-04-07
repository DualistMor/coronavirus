package com.bohdanserdyuk.CoronavirusApp.servlets;

import com.bohdanserdyuk.CoronavirusApp.data.ejb.impl.TreatmentEjb;
import com.bohdanserdyuk.CoronavirusApp.data.constants.ParamsConstants;
import com.bohdanserdyuk.CoronavirusApp.data.models.Treatment;
import com.bohdanserdyuk.CoronavirusApp.data.models.Infected;

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
        String vaccineCode = req.getParameter("vaccineCode");
        String infectedPhone = ((Infected)req.getSession().getAttribute(ParamsConstants.USER)).getPhoneNumber();

        Treatment treatment = treatmentEjb.getInfectedTreatmentByVaccine(infectedPhone, vaccineCode);

        if (treatment != null) {
            rd = req.getRequestDispatcher("treatment.jsp");
            req.setAttribute("treatment", treatment);
        } else {
            rd = req.getRequestDispatcher("orderTreatment.jsp");
            req.setAttribute("vaccineCode", vaccineCode);
        }

        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String vaccineCode = req.getParameter("vaccineCode");
        String infectedPhone = ((Infected)req.getSession().getAttribute(ParamsConstants.USER)).getPhoneNumber();

        treatmentEjb.createTreatment(vaccineCode, infectedPhone);
        resp.sendRedirect("vaccines");
    }
}
