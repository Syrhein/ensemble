package smhrd.controller;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import smhrd.model.MusicalDAO;

@WebServlet("/IncrementViewCon")
public class IncrementViewCon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // CORS 설정 추가
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        String musicalId = null;

        try {
            // JSON 데이터 파싱
            BufferedReader reader = request.getReader();
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            JsonObject jsonObject = JsonParser.parseString(json.toString()).getAsJsonObject();
            musicalId = jsonObject.get("musicalId").getAsString();

            if (musicalId == null || musicalId.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\":\"뮤지컬 ID가 필요합니다.\"}");
                return;
            }

            // DAO 호출
            MusicalDAO dao = new MusicalDAO();
            boolean updated = dao.incrementMusicalViews(musicalId);

            if (updated) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("{\"success\":\"조회수가 증가했습니다.\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\":\"뮤지컬을 찾을 수 없습니다.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"서버 에러가 발생했습니다.\"}");
        }
    }
}
