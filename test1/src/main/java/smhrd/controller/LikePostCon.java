package smhrd.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import smhrd.model.BoardDAO;

@WebServlet("/LikePostCon")
public class LikePostCon extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        int postIdx = Integer.parseInt(request.getParameter("postIdx"));

        // 좋아요 증가 처리
        BoardDAO dao = new BoardDAO();
        int updatedLikes = dao.incrementPostLikes(postIdx);

        // JSON 응답
        response.getWriter().write("{\"likes\": " + updatedLikes + "}");
    }
}
