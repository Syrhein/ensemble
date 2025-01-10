package smhrd.controller;


import org.json.JSONArray;
import org.json.JSONObject;
import smhrd.model.MusicalDAO;
import smhrd.model.MusicalVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/SlideDataServlet")
public class SlideDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        MusicalDAO dao = new MusicalDAO();
        List<MusicalVO> topMusicals = dao.getTopMusicals(); // DAO 호출

        JSONArray jsonArray = new JSONArray();
        for (MusicalVO musical : topMusicals) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("musicalId", musical.getMusicalId());
            jsonObject.put("musicalTitle", musical.getMusicalTitle());
            jsonObject.put("musicalPoster", musical.getMusicalPoster());
            jsonArray.put(jsonObject);
        }

        response.getWriter().write(jsonArray.toString());
    }
}

