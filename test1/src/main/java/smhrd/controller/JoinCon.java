package smhrd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import smhrd.model.Member;
import smhrd.model.MemberDAO;

@WebServlet("/JoinCon")
public class JoinCon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 폼에서 전달된 데이터 수집
        String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String userTel = request.getParameter("userTel");

        // Member 객체 생성 및 데이터 설정
        Member member = new Member();
        member.setUserId(userId);
        member.setUserPw(userPw);
        member.setUserName(userName);
        member.setUserEmail(userEmail);
        member.setUserTel(userTel);

        // DAO 호출
        MemberDAO dao = new MemberDAO();
        int result = dao.join(member);

        // 결과에 따라 페이지 이동
        if (result > 0) {
            RequestDispatcher rd = request.getRequestDispatcher("Main.html");
            rd.forward(request, response);
        } else {
            response.sendRedirect("signUp.html");
        }
    }
}
