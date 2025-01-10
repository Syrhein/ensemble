package smhrd.controller;

import smhrd.model.MusicalDAO;
import smhrd.model.MusicalVO;
import smhrd.model.MusicalDetailVO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/MainCon")
public class MainCon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // CORS 설정
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        String musicalId = request.getParameter("musicalId");
        String showDetails = request.getParameter("showDetails");

        try {
            MusicalDAO dao = new MusicalDAO();
            Gson gson = new Gson();

            if (musicalId != null && !musicalId.trim().isEmpty()) {
                if ("true".equalsIgnoreCase(showDetails)) {
                    MusicalDetailVO details = dao.getMusicalDetails(musicalId);
                    if (details != null) {
                        response.getWriter().write(gson.toJson(details));
                    } else {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        response.getWriter().write("{\"error\":\"뮤지컬 상세 정보를 찾을 수 없습니다.\"}");
                    }
                }
            } else {
                List<MusicalVO> musicals = dao.getMusicalList();
                response.getWriter().write(gson.toJson(musicals));
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"서버 에러가 발생했습니다.\"}");
        }
    }
}