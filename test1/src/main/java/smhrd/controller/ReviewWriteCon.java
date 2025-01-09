package smhrd.controller;

import smhrd.model.MusicalDAO;
import smhrd.model.ReviewVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ReviewWriteCon")
public class ReviewWriteCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			// 파라미터 가져오기 및 검증
			String userId = request.getParameter("userId");
			String reviewContent = request.getParameter("reviewContent");
			String reviewStar = request.getParameter("reviewStar");
			String showIdxParam = request.getParameter("showIdx");

			// 디버깅 로그
			System.out.println("Received showIdxParam: " + showIdxParam);

			// Null 또는 빈 값 검증
			if (showIdxParam == null || showIdxParam.trim().isEmpty()) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("{\"error\":\"showIdx 값이 없습니다.\"}");
				return;
			}

			// 숫자 변환 및 예외 처리
			int showIdx;
			try {
				showIdx = Integer.parseInt(showIdxParam);
			} catch (NumberFormatException e) {
				System.out.println("Invalid showIdxParam: " + showIdxParam); // 디버깅 로그
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("{\"error\":\"showIdx는 숫자여야 합니다.\"}");
				return;
			}

			// ReviewVO 객체 생성
			ReviewVO review = new ReviewVO();
			review.setUserId(userId);
			review.setReviewContent(reviewContent);
			review.setReviewStar(reviewStar);
			review.setShowIdx(showIdx);

			// DAO를 통해 데이터베이스에 리뷰 삽입
			MusicalDAO dao = new MusicalDAO();
			boolean success = dao.insertReview(review);
			System.out.println("Received showIdxParam: " + showIdxParam);

			if (success) {
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().write("{\"success\":\"리뷰가 성공적으로 작성되었습니다.\"}");
			} else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().write("{\"error\":\"리뷰 작성에 실패하였습니다.\"}");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("{\"error\":\"서버 에러가 발생하였습니다.\"}");
		}
	}
}
