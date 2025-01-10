package smhrd.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import smhrd.model.MusicalDAO;
import smhrd.model.ReviewVO;

public class ReviewWriteCon extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // 요청 데이터 가져오기
        String showIdx = request.getParameter("showIdx");
        String userId = request.getParameter("userId");
        String reviewContent = request.getParameter("reviewContent");
        String reviewStar = request.getParameter("reviewStar"); // String 타입으로 입력받음

        try {
            // ReviewVO 객체 생성 및 데이터 설정
            ReviewVO review = new ReviewVO();
            review.setShowIdx(Integer.parseInt(showIdx)); // String -> int 변환
            review.setUserId(userId);
            review.setReviewContent(reviewContent);
            review.setReviewStar(Integer.parseInt(reviewStar)); // String -> int 변환

            // DAO 호출
            MusicalDAO dao = new MusicalDAO();
            int result = dao.insertReview(review); // int 반환
            boolean success = (result > 0); // 삽입 성공 여부 확인

            // 결과 처리
            if (success) {
                response.getWriter().write("리뷰 작성 성공");
            } else {
                response.getWriter().write("리뷰 작성 실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.getWriter().write("리뷰 작성 중 오류 발생");
            } catch (Exception ignored) {
            }
        }
    }
}
