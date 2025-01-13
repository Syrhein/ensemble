package smhrd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import smhrd.model.MusicalDAO;

@WebServlet("/DeleteReviewCon")
public class DeleteReviewCon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");

        String reviewIdParam = request.getParameter("reviewId");
        System.out.println("요청받은 reviewId: " + reviewIdParam); // 디버깅용 로그 추가

        if (reviewIdParam == null || reviewIdParam.isEmpty()) {
            response.getWriter().write("{\"success\": false, \"error\": \"리뷰 ID가 제공되지 않았습니다.\"}");
            return;
        }

        try {
            int reviewId = Integer.parseInt(reviewIdParam); // 숫자로 변환 시도
            MusicalDAO dao = new MusicalDAO();
            boolean isDeleted = dao.deleteReviewById(reviewId);

            if (isDeleted) {
                response.getWriter().write("{\"success\": true}");
            } else {
                response.getWriter().write("{\"success\": false, \"error\": \"리뷰 삭제에 실패했습니다.\"}");
            }
        } catch (NumberFormatException e) {
            System.err.println("잘못된 reviewId 형식: " + reviewIdParam); // 디버깅용 로그 추가
            response.getWriter().write("{\"success\": false, \"error\": \"리뷰 ID가 잘못된 형식입니다.\"}");
        } catch (Exception e) {
            response.getWriter().write("{\"success\": false, \"error\": \"서버 처리 중 오류가 발생했습니다.\"}");
            e.printStackTrace();
        }
    }
}
