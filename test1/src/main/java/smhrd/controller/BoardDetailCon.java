package smhrd.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import smhrd.model.BoardDAO;
import smhrd.model.BoardVO;
import smhrd.model.CommentVO;

@WebServlet("/BoardDetailCon")
public class BoardDetailCon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postIdxParam = request.getParameter("postIdx");
        int postIdx;

        try {
            postIdx = Integer.parseInt(postIdxParam);
            System.out.println("[INFO] Received Post Index: " + postIdx);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid Post Index: " + postIdxParam);
            response.sendRedirect("Board.jsp");
            return;
        }

        BoardDAO dao = new BoardDAO();

        // 조회수 증가
        dao.incrementViewCount(postIdx);

        // 게시글 상세 데이터 가져오기
        BoardVO board = dao.getBoardDetail(postIdx);
        if (board == null) {
            System.out.println("[ERROR] No board found for Post Index: " + postIdx);
            response.sendRedirect("Board.jsp");
            return;
        }

        // 댓글 데이터 가져오기
        List<CommentVO> comments = dao.getComments(postIdx);

        // 게시글 및 댓글 데이터를 리퀘스트에 저장
        request.setAttribute("board", board);
        request.setAttribute("comments", comments);
        request.getRequestDispatcher("BoardDetail.jsp").forward(request, response);
    }
}
