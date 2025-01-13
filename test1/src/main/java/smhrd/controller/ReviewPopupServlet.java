package smhrd.controller;

import smhrd.model.MusicalDAO;
import smhrd.model.ReviewVO;
import smhrd.model.Member;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/reviewPopup")
public class ReviewPopupServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 세션에서 USER_ID 확인
        HttpSession session = request.getSession(false); // 세션이 없으면 null 반환
        String userId = null;

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

        // DAO를 통해 리뷰 목록 가져오기
        MusicalDAO dao = new MusicalDAO(); // 기본 생성자 사용
        List<ReviewVO> reviewList = dao.getReviewList(userId);

        if (reviewList == null || reviewList.isEmpty()) {
            System.out.println("[INFO] 리뷰 데이터가 없습니다.");
        }

        // 리뷰 데이터를 JSP에 전달
        request.setAttribute("reviewList", reviewList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("reviewPopup.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
