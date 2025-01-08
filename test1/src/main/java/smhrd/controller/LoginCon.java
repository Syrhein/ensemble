package smhrd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import smhrd.model.Member;
import smhrd.model.MemberDAO;

@WebServlet("/LoginCon")
public class LoginCon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 폼에서 전달된 데이터 수집
        String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");

        // DAO 호출
        MemberDAO dao = new MemberDAO();
        Member member = dao.login(userId, userPw);

        // 로그인 성공 여부 확인
        if (member != null) {
            HttpSession session = request.getSession();
            session.setAttribute("info", member);
            session.setAttribute("user", member.getUserName()); // 이름을 세션에 저장
            RequestDispatcher dispatcher = request.getRequestDispatcher("Main.html");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("login.html");
        }
    }
}
