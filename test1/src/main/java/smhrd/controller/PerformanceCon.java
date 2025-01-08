package smhrd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import smhrd.model.MusicalDAO;
import smhrd.model.PerformanceVO;

@WebServlet("/PerformanceCon")
public class PerformanceCon extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String musicalIdx = request.getParameter("musicalIdx");

        try {
            MusicalDAO dao = new MusicalDAO();
            PerformanceVO details = dao.getPerformanceDetails(musicalIdx);

            Gson gson = new Gson();
            String json = gson.toJson(details);
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

