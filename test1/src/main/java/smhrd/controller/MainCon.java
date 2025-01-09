package smhrd.controller;

import smhrd.model.MusicalDAO;
import smhrd.model.MusicalVO;
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

        try {
            // DAO를 통해 뮤지컬 목록 가져오기
            MusicalDAO dao = new MusicalDAO();
            List<MusicalVO> musicals = dao.getMusicalList();

            // JSON으로 변환하여 응답
            Gson gson = new Gson();
            String json = gson.toJson(musicals);
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
