package smhrd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import smhrd.model.Member; // Member 클래스 임포트 필요

@WebServlet("/CheckLoginCon")
public class CheckLoginCon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("info"); // 세션에서 Member 객체 가져오기

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (member != null) {
            // 로그인된 사용자 정보를 JSON으로 반환
            response.getWriter().write("{\"isLoggedIn\": true, \"userId\": \"" + member.getUserId() + "\"}");
        } else {
            response.getWriter().write("{\"isLoggedIn\": false, \"userId\": null}");
        }
    }
}
