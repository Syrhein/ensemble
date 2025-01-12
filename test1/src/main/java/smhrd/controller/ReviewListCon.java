package smhrd.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import smhrd.model.MusicalDAO;
import smhrd.model.ReviewVO;

@WebServlet("/ReviewListCon")
public class ReviewListCon extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String showIdx = request.getParameter("showIdx");

        // showIdx가 없으면 에러 반환
        if (showIdx == null || showIdx.isEmpty()) {
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write("{\"error\": \"공연 ID가 제공되지 않았습니다.\"}");
            return;
        }

        // DAO에서 리뷰 가져오기
        try {
            MusicalDAO dao = new MusicalDAO();
            List<ReviewVO> reviews = dao.getReviewsByShowIdx(Integer.parseInt(showIdx));

            // JSON으로 응답
            response.setContentType("application/json; charset=UTF-8");
            Gson gson = new Gson();
            String json = gson.toJson(reviews); // List<ReviewVO>를 JSON으로 변환
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write("{\"error\": \"리뷰 데이터를 가져오는 중 오류가 발생했습니다.\"}");
        }
    }
}
