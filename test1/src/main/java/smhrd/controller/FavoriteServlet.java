package smhrd.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import smhrd.model.FavoriteVO;
import smhrd.model.MusicalDAO;

@WebServlet("/FavoriteServlet")
public class FavoriteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        String userId = request.getParameter("userId");
        String showIdx = request.getParameter("showIdx");

        // userId 또는 showIdx가 없는 경우 오류 처리
        if (userId == null || showIdx == null) {
            response.getWriter().write("{\"success\": false, \"error\": \"유효하지 않은 요청입니다.\"}");
            return;
        }

        try {
            MusicalDAO dao = new MusicalDAO();
            FavoriteVO favorite = new FavoriteVO(userId, Integer.parseInt(showIdx));

            // 관심 공연 등록 여부 확인
            if (dao.isFavoriteExists(favorite)) {
                // 이미 등록된 경우 -> 관심 공연 삭제
                boolean removed = dao.removeFavorite(favorite);
                response.getWriter().write("{\"success\": true, \"removed\": " + removed + "}");
            } else {
                // 등록되지 않은 경우 -> 관심 공연 추가
                boolean added = dao.addFavorite(favorite);
                response.getWriter().write("{\"success\": true, \"added\": " + added + "}");
            }
        } catch (Exception e) {
            response.getWriter().write("{\"success\": false, \"error\": \"서버 처리 중 오류가 발생했습니다.\"}");
        }
    }
}
