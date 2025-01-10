// ReviewWriteCon.java
package smhrd.controller;

import smhrd.model.MusicalDAO;
import smhrd.model.ReviewVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ReviewWriteCon")
public class ReviewWriteCon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String userId = request.getParameter("userId");
            String reviewContent = request.getParameter("reviewContent");
            String reviewStar = request.getParameter("reviewStar");
            String showIdx = request.getParameter("showIdx");

            if (showIdx == null || showIdx.trim().isEmpty() || !isNumeric(showIdx)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\":\"showIdx는 유효한 숫자여야 합니다.\"}");
                return;
            }
            
            ReviewVO review = new ReviewVO();
            review.setShowIdx(Integer.parseInt(showIdx));
            review.setUserId(userId);
            review.setReviewContent(reviewContent);
            review.setReviewStar(reviewStar);

            MusicalDAO dao = new MusicalDAO();
            boolean success = dao.insertReview(review);

            if (success) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("{\"success\":\"리뷰가 성공적으로 작성되었습니다.\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"error\":\"리뷰 작성에 실패하였습니다.\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"서버 에러가 발생하였습니다.\"}");
        }
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}