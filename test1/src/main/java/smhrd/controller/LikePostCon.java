package smhrd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import smhrd.model.BoardDAO;

@WebServlet("/LikePostCon")
public class LikePostCon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 게시글 번호 가져오기
        String postIdxParam = request.getParameter("postIdx");
        int postIdx = 0;

        try {
            postIdx = Integer.parseInt(postIdxParam);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // 좋아요 증가 처리
        BoardDAO dao = new BoardDAO();
        dao.incrementPostLikes(postIdx);

        // 성공 응답
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
