package smhrd.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import smhrd.model.MusicalDAO;
import smhrd.model.MusicalVO;


@WebServlet("/SlideDataServlet")
public class SlideDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 응답의 Content-Type 설정 (JSON 형식, UTF-8 인코딩)
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // DAO 호출하여 데이터 가져오기
        MusicalDAO dao = new MusicalDAO();
        List<MusicalVO> topMusicals = dao.getTopMusicalPosters();

        // 데이터를 JSON 배열로 변환
        JSONArray jsonArray = new JSONArray();
        for (MusicalVO musical : topMusicals) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("musicalId", musical.getMusicalId());
            jsonObject.put("musicalTitle", musical.getMusicalTitle());
            jsonObject.put("musicalPoster", musical.getMusicalPoster());
            jsonArray.put(jsonObject);
        }

        // JSON 응답 반환
        response.getWriter().write(jsonArray.toString());
    }
}
