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
        int cmtIdx = Integer.parseInt(request.getParameter("cmtIdx"));

        BoardDAO dao = new BoardDAO();
        int result = dao.incrementCommentLikes(cmtIdx);

        if (result > 0) {
            response.sendRedirect(request.getHeader("Referer"));
        } else {
            response.getWriter().println("좋아요 처리 실패");
        }
    }
}

