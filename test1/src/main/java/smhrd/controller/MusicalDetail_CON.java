package smhrd.controller;

import com.google.gson.Gson;
import smhrd.model.MusicalDAO;
import smhrd.model.MusicalDetailVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MusicalDetail_CON") // 서블릿 경로 설정
public class MusicalDetail_CON extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        // 클라이언트로부터 musicalId를 받음
        String musicalId = request.getParameter("musicalId");
        
        if (musicalId == null || musicalId.trim().isEmpty()) {
            response.getWriter().write("{\"error\": \"뮤지컬 ID가 제공되지 않았습니다.\"}");
            return;
        }

        try {
            // DAO를 통해 데이터 조회
            MusicalDAO dao = new MusicalDAO();
            MusicalDetailVO musicalDetail = dao.getMusicalDetails(musicalId);

            if (musicalDetail != null) {
                // 데이터를 JSON으로 변환하여 클라이언트에 전송
                Gson gson = new Gson();
                String json = gson.toJson(musicalDetail);
                response.getWriter().write(json);
            } else {
                response.getWriter().write("{\"error\": \"해당 뮤지컬 정보를 찾을 수 없습니다.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"error\": \"서버에서 오류가 발생했습니다.\"}");
        }
    }
}
