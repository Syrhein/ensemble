package smhrd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import smhrd.model.BoardDAO;
import smhrd.model.BoardVO;

@WebServlet("/BoardDetailCon")
public class BoardDetailCon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 게시글 번호 파라미터 가져오기
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

        // 게시글 상세 데이터 가져오기 (조회수 증가 포함)
        BoardVO board = dao.getBoardDetail(postIdx);

        if (board == null) {
            System.out.println("[ERROR] No board found for Post Index: " + postIdx);
            response.sendRedirect("Board.jsp");
            return;
        }

        // 게시글 데이터를 리퀘스트에 저장하고 상세 페이지로 이동
        request.setAttribute("board", board);
        request.getRequestDispatcher("BoardDetail.jsp").forward(request, response);
    }
}
