package smhrd.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import smhrd.model.MusicalDAO;
import smhrd.model.ReviewVO;

@WebServlet("/ReviewWriteCon")
public class ReviewWriteCon extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json; charset=UTF-8");
        try {
            // 요청 데이터 가져오기
            String showIdx = request.getParameter("showIdx");
            String userId = request.getParameter("userId");
            String reviewContent = request.getParameter("reviewContent");
            String reviewStar = request.getParameter("reviewStar");

            if (showIdx == null || userId == null || reviewContent == null || reviewStar == null) {
                response.getWriter().write("{\"success\": false, \"error\": \"유효하지 않은 요청입니다.\"}");
                return;
            }

            // ReviewVO 객체 생성 및 데이터 설정
            ReviewVO review = new ReviewVO();
            review.setShowIdx(Integer.parseInt(showIdx));
            review.setUserId(userId);
            review.setReviewContent(reviewContent);
            review.setReviewStar(Integer.parseInt(reviewStar));

            // DAO 호출
            MusicalDAO dao = new MusicalDAO();
            int result = dao.insertReview(review);

            // 결과 처리
            if (result > 0) {
                response.getWriter().write("{\"success\": true}");
            } else {
                response.getWriter().write("{\"success\": false, \"error\": \"리뷰 저장 실패\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.getWriter().write("{\"success\": false, \"error\": \"서버 오류 발생\"}");
            } catch (Exception ignored) {
            }
        }
    }
}
