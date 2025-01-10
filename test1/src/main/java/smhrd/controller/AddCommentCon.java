package smhrd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import smhrd.model.BoardDAO;
import smhrd.model.CommentVO;

@WebServlet("/AddCommentCon")
public class AddCommentCon extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 게시글 ID
        int postIdx = Integer.parseInt(request.getParameter("postIdx"));

        // 세션에서 사용자 ID 가져오기
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        // userId가 없는 경우 처리
        if (userId == null || userId.trim().isEmpty()) {
            response.getWriter().println("로그인이 필요합니다.");
            return;
        }

        // 댓글 내용 가져오기
        String cmtContent = request.getParameter("cmtContent");

        // 댓글 데이터 저장
        CommentVO comment = new CommentVO();
        comment.setPostIdx(postIdx);
        comment.setUserId(userId);
        comment.setCmtContent(cmtContent);

        // DAO를 통해 데이터베이스에 삽입
        BoardDAO dao = new BoardDAO();
        int result = dao.insertComment(comment);

        // 결과 처리
        if (result > 0) {
            response.sendRedirect("BoardDetailCon?postIdx=" + postIdx);
        } else {
            response.getWriter().println("댓글 작성 실패");
        }
    }
}
