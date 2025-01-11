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
        String musicalId = request.getParameter("musicalId");
        

        if (userId == null || musicalId == null) {
            response.getWriter().write("{\"success\": false, \"error\": \"유효하지 않은 요청입니다.\"}");
            return;
        }

        try {
            // DAO 생성
            MusicalDAO dao = new MusicalDAO();

            // musicalId를 사용해 showIdx 가져오기
            String showIdx = request.getParameter("showIdx");
            if (showIdx == null) {
                response.getWriter().write("{\"success\": false, \"error\": \"해당 뮤지컬의 공연 정보가 없습니다.\"}");
                return;
            }

            // 관심 데이터 생성
            FavoriteVO favorite = new FavoriteVO(userId, Integer.parseInt(showIdx));

            // 중복 확인
            if (dao.isFavoriteExists(favorite)) {
                response.getWriter().write("{\"success\": false, \"error\": \"이미 등록된 관심 공연입니다.\"}");
                return;
            }

            // 관심 등록
            boolean success = dao.addFavorite(favorite);
            response.getWriter().write("{\"success\": " + success + "}");
        } catch (Exception e) {
            response.getWriter().write("{\"success\": false, \"error\": \"서버 처리 중 오류가 발생했습니다.\"}");
        }
    }
}
