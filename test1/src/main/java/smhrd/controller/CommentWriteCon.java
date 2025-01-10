package smhrd.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import smhrd.model.BoardDAO;
import smhrd.model.CommentVO;

@WebServlet("/CommentWriteCon")
public class CommentWriteCon extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int postIdx = Integer.parseInt(request.getParameter("postIdx"));
        String userId = (String) request.getSession().getAttribute("userId");
        String cmtContent = request.getParameter("cmtContent");

        CommentVO comment = new CommentVO();
        comment.setPostIdx(postIdx);
        comment.setUserId(userId);
        comment.setCmtContent(cmtContent);

        BoardDAO dao = new BoardDAO();
        int result = dao.insertComment(comment);

        if (result > 0) {
            response.sendRedirect("BoardDetailCon?postIdx=" + postIdx);
        } else {
            response.getWriter().println("댓글 작성 실패");
        }
    }
}
