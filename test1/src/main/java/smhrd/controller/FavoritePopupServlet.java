package smhrd.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import smhrd.model.Member;
import smhrd.model.MusicalDAO;
import smhrd.model.MyPageVO;

@WebServlet("/favoritePopup")
public class FavoritePopupServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 세션에서 USER_ID 확인
        String userId = null;
        HttpSession session = request.getSession(false); // 세션이 없으면 null 반환
        if (session != null) {
            Member member = (Member) session.getAttribute("info");
            if (member != null) {
                userId = member.getUserId();
            }
        }


        if (userId == null) {
            // 세션이 없거나 USER_ID가 없는 경우
            response.getWriter().write("<html><body><h3>세션이 만료되었거나 로그인이 필요합니다.</h3></body></html>");
            return; // 이후 코드 실행 중단
        }

        // DAO를 통해 관심 등록 목록 가져오기
        MusicalDAO dao = new MusicalDAO();
        List<MyPageVO> favoriteList = dao.getFavoriteList(userId);

        // 관심 등록 목록을 JSP에 전달
        request.setAttribute("favoriteList", favoriteList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/favoritePopup.jsp");
        dispatcher.forward(request, response);
    }
}


