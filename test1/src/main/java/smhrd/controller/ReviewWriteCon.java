package smhrd.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import smhrd.model.MusicalDAO;
import smhrd.model.ReviewVO;

@WebServlet("/ReviewWriteCon")
public class ReviewWriteCon extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userId"); // 세션에서 userId 가져오기

            if (userId == null) {
                response.getWriter().write("리뷰 작성 실패: 로그인 정보 없음");
                return;
            }

            String showIdx = request.getParameter("showIdx");
            String reviewContent = request.getParameter("reviewContent");
            String reviewStar = request.getParameter("reviewStar");

            ReviewVO review = new ReviewVO();
            review.setShowIdx(Integer.parseInt(showIdx));
            review.setUserId(userId);
            review.setReviewContent(reviewContent);
            review.setReviewStar(Integer.parseInt(reviewStar));

            MusicalDAO dao = new MusicalDAO();
            int result = dao.insertReview(review);

            if (result > 0) {
                response.getWriter().write("리뷰 작성 성공");
            } else {
                response.getWriter().write("리뷰 작성 실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.getWriter().write("리뷰 작성 중 오류 발생");
            } catch (IOException ignored) {}
        }
    }
}

