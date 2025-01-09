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


@WebServlet("/calendar")
public class calendar extends HttpServlet {
    private static final String DB_URL = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1523:xe";
    private static final String DB_USER = "sc_24K_bigdata15_p2_1";
    private static final String DB_PASSWORD = "smhrd1";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        JsonArray events = new JsonArray();

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("info"); // 세션에서 사용자 정보 가져오기

        if (member == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"로그인이 필요합니다.\"}");
            return;
        }

        String userId = member.getUserId(); // 세션에서 사용자 ID 가져오기

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(
                     "SELECT m.musical_name AS title, " +
                     "       r.review_date AS start, " +
                     "       m.musical_poster AS poster_url " +
                     "FROM review r " +
                     "JOIN musical m ON r.musical_id = m.musical_id " +
                     "WHERE r.user_id = ?"
                 )) {

                stmt.setString(1, userId); // 사용자 ID 바인딩
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    JsonObject event = new JsonObject();
                    event.addProperty("title", rs.getString("title"));
                    event.addProperty("start", rs.getDate("start").toString());
                    event.addProperty("posterUrl", rs.getString("poster_url"));
                    events.add(event);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        response.getWriter().write(events.toString());
    }
}