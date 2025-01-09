package smhrd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import smhrd.model.Member; // Member 클래스 임포트 필요

@WebServlet("/CheckLoginCon")
public class CheckLoginCon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // 세션이 없으면 null 반환
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject jsonResponse = new JSONObject();

        try {
            if (session != null) {
                Member member = (Member) session.getAttribute("info"); // 세션에서 Member 객체 가져오기

                if (member != null) {
                    // 로그인 상태
                    jsonResponse.put("isLoggedIn", true);
                    jsonResponse.put("userId", member.getUserId());
                    jsonResponse.put("userEmail", member.getUserEmail()); // 필요한 추가 정보
                } else {
                    // 세션은 있지만 로그인 정보가 없는 경우
                    jsonResponse.put("isLoggedIn", false);
                    jsonResponse.put("error", "No user information found in session.");
                }
            } else {
                // 세션 자체가 없는 경우
                jsonResponse.put("isLoggedIn", false);
                jsonResponse.put("error", "Session not found.");
            }

            response.getWriter().write(jsonResponse.toString());
        } catch (Exception e) {
            // 예외 처리
            jsonResponse.put("isLoggedIn", false);
            jsonResponse.put("error", "An unexpected error occurred: " + e.getMessage());
            response.getWriter().write(jsonResponse.toString());
        }
    }
}
