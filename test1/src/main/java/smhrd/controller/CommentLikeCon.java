package smhrd.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import smhrd.model.BoardDAO;
import smhrd.model.CommentVO;

@WebServlet("/CommentLikeCon")
public class CommentLikeCon extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        int cmtIdx = Integer.parseInt(request.getParameter("cmtIdx"));

        BoardDAO dao = new BoardDAO();
        int updatedLikes = dao.incrementCommentLikes(cmtIdx);

        if (updatedLikes >= 0) {
            response.getWriter().write("{\"likes\": " + updatedLikes + "}");
        } else {
            response.getWriter().write("{\"error\": \"좋아요 처리 실패\"}");
        }
    }
}

