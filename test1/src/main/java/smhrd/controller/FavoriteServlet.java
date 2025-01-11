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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        String userId = request.getParameter("userId");
        String showIdxStr = request.getParameter("showIdx");

        if (userId == null || showIdxStr == null) {
            response.getWriter().write("{\"success\": false, \"error\": \"유효하지 않은 요청입니다.\"}");
            return;
        }

        try {
            int showIdx = Integer.parseInt(showIdxStr);

            // 관심 데이터 생성
            FavoriteVO favorite = new FavoriteVO(userId, showIdx);

            // DAO 호출
            MusicalDAO dao = new MusicalDAO();
            
            // 중복 확인
            if (dao.isFavoriteExists(favorite)) {
                response.getWriter().write("{\"success\": false, \"error\": \"이미 등록된 관심 공연입니다.\"}");
                return;
            }

            // 관심 등록
            boolean success = dao.addFavorite(favorite);
            response.getWriter().write("{\"success\": " + success + "}");
        } catch (NumberFormatException e) {
            response.getWriter().write("{\"success\": false, \"error\": \"잘못된 쇼 ID.\"}");
        }
    }
}
