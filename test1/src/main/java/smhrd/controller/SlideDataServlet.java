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


@WebServlet("/SlideDataServlet")
public class SlideDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        MusicalDAO dao = new MusicalDAO();
        List<String> topPosters = dao.getTopMusicalPosters();


        JSONArray jsonArray = new JSONArray();

        // 데이터가 없을 경우 처리
        if (topPosters == null || topPosters.isEmpty()) {
            System.out.println("No data found!");
            response.getWriter().write(jsonArray.toString()); // 빈 JSON 반환
            return;
        }

        for (String poster : topPosters) {
            if (poster == null) continue; // Null 데이터 건너뜁니다.
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("musicalPoster", poster); // 포스터 URL만 JSON으로 반환
            jsonArray.put(jsonObject);
        }

        response.getWriter().write(jsonArray.toString());
    }
}

