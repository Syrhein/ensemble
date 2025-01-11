package smhrd.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import smhrd.model.BoardDAO;

@WebServlet("/LikePostCon")
public class LikePostCon extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 데이터 인코딩 설정
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        // 파라미터 유효성 검사 및 처리
        String postIdxStr = request.getParameter("postIdx");
        if (postIdxStr == null || postIdxStr.trim().isEmpty()) {
            // 잘못된 요청 처리
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid or missing postIdx parameter.\"}");
            return;
        }

        int postIdx;
        try {
            postIdx = Integer.parseInt(postIdxStr.trim());
        } catch (NumberFormatException e) {
            // 숫자 변환 실패 시 응답 처리
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"postIdx must be a valid number.\"}");
            return;
        }

        // 좋아요 증가 처리
        try {
            BoardDAO dao = new BoardDAO();
            int updatedLikes = dao.incrementPostLikes(postIdx);

            // JSON 응답
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"likes\": " + updatedLikes + "}");
        } catch (Exception e) {
            // 좋아요 처리 실패 시 에러 응답
            e.printStackTrace(); // 서버 로그에 예외 출력
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Failed to update likes.\"}");
        }
    }
}
