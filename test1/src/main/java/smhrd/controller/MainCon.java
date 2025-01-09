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

        // CORS 설정 추가
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // musicalId와 showDetails 파라미터 처리
        String musicalId = request.getParameter("musicalId");
        String showDetails = request.getParameter("showDetails"); // 공연 세부 정보 요청 여부

        try {
            MusicalDAO dao = new MusicalDAO();
            Gson gson = new Gson();

            if (musicalId != null) {
                if ("true".equalsIgnoreCase(showDetails)) {
                    // 공연 상세 정보 반환
                    MusicalDetailVO details = dao.getMusicalDetails(musicalId);
                    String json = gson.toJson(details);
                    response.getWriter().write(json);
                } else {
                    // 특정 뮤지컬 기본 정보 반환
                    MusicalVO musical = dao.getMusicalById(musicalId);
                    String json = gson.toJson(musical);
                    response.getWriter().write(json);
                }
            } else {
                // 전체 뮤지컬 목록 반환
                List<MusicalVO> musicals = dao.getMusicalList();
                String json = gson.toJson(musicals);
                response.getWriter().write(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
