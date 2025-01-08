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
        // 1. 요청에서 게시글 ID 가져오기
        String idParam = request.getParameter("id");
        int id = 0;

        try {
            id = Integer.parseInt(idParam);
            System.out.println("Received ID: " + id); // 디버깅 로그
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID: " + idParam); // 디버깅 로그
            response.sendRedirect("Board.jsp");
            return;
        }

        // 2. DAO 호출
        BoardDAO dao = new BoardDAO();

        // 조회수 증가
        dao.incrementViewCount(id);

        // 게시글 상세 데이터 가져오기
        BoardVO board = dao.getBoardDetail(id);
        if (board == null) {
            System.out.println("No board found for ID: " + id); // 디버깅 로그
            response.sendRedirect("Board.jsp");
            return;
        }

        // 3. 데이터 저장 및 페이지 이동
        request.setAttribute("board", board);
        request.getRequestDispatcher("BoardDetail.jsp").forward(request, response);
    }
}
