package com.bohdanserdyuk.CoronavirusApp.servlets;

import com.bohdanserdyuk.CoronavirusApp.model.entities.Infected;
import com.bohdanserdyuk.CoronavirusApp.model.constants.ParamsConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "IndexServlet", urlPatterns = {"/"})
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        if (session.isNew() || !(session.getAttribute(ParamsConstants.DOCTOR) instanceof Infected)) {
            resp.sendRedirect("auth.jsp");
            return;
        }
        resp.sendRedirect("infected");
    }
}
