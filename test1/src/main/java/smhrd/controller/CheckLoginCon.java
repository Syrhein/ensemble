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

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false); // 세션이 없으면 null 반환
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject jsonResponse = new JSONObject();
        try {
            if (session != null) {
                Member member = (Member) session.getAttribute("info");

                if (member != null) {
                    jsonResponse.put("isLoggedIn", true);
                    jsonResponse.put("userId", member.getUserId());
                    jsonResponse.put("userEmail", member.getUserEmail());
                } else {
                    jsonResponse.put("isLoggedIn", false);
                    jsonResponse.put("error", "로그인 정보가 없습니다.");
                }
            } else {
                jsonResponse.put("isLoggedIn", false);
                jsonResponse.put("error", "세션이 만료되었습니다.");
            }
        } catch (Exception e) {
            jsonResponse.put("isLoggedIn", false);
            jsonResponse.put("error", "서버 오류가 발생했습니다.");
            e.printStackTrace();
        }

        response.getWriter().write(jsonResponse.toString());
    }
}

