package smhrd.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import smhrd.model.Member;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1523:xe";
    private static final String DB_USER = "sc_24K_bigdata15_p2_1";
    private static final String DB_PASSWORD = "smhrd1";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        JsonArray events = new JsonArray();

        // 세션에서 사용자 정보 가져오기
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("info"); // 세션에서 사용자 정보 확인

        if (member == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"로그인이 필요합니다.\"}");
            return;
        }

        String userId = member.getUserId(); // 세션에서 사용자 ID 가져오기

        try {
            // JDBC 드라이버 로드
            Class.forName("oracle.jdbc.OracleDriver");

            // 관심 등록 데이터를 조회하는 SQL 쿼리
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(
                     "SELECT " +
                     "    m.MUSICAL_TITLE AS \"title\", " +
                     "    f.CREATED_AT AS \"start\", " +
                     "    m.MUSICAL_POSTER AS \"poster_url\", " +
                     "    m.MUSICAL_ID AS \"musical_id\", " +
                     "    f.SHOW_IDX AS \"show_idx\" " +
                     "FROM " +
                     "    tb_favorite f " +
                     "JOIN " +
                     "    tb_show s ON f.SHOW_IDX = s.SHOW_IDX " +
                     "JOIN " +
                     "    tb_musical m ON s.MUSICAL_ID = m.MUSICAL_ID " +
                     "WHERE " +
                     "    f.USER_ID = ?"
                 )) {

                // 사용자 ID 바인딩
                stmt.setString(1, userId);
                ResultSet rs = stmt.executeQuery();

                // 결과를 JSON 배열로 변환
                while (rs.next()) {
                    JsonObject event = new JsonObject();
                    event.addProperty("title", rs.getString("title")); // 뮤지컬 제목
                    event.addProperty("start", rs.getDate("start").toString()); // 관심 등록 날짜
                    event.addProperty("posterUrl", rs.getString("poster_url")); // 포스터 이미지 URL
                    event.addProperty("musicalId", rs.getString("musical_id")); // 뮤지컬 ID
                    event.addProperty("showIdx", rs.getInt("show_idx")); // 공연 IDX
                    events.add(event);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Database error occurred.\"}");
            return;
        }

        // JSON 응답 반환
        response.getWriter().write(events.toString());
    }
}
