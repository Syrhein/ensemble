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
                     "SELECT " +
                     "    m.MUSICAL_TITLE AS \"title\", " +
                     "    r.CREATED_AT AS \"start\", " +
                     "    m.MUSICAL_POSTER AS \"poster_url\" " +
                     "FROM " +
                     "    tb_review r " +
                     "JOIN " +
                     "    tb_show s ON r.SHOW_IDX = s.SHOW_IDX " +
                     "JOIN " +
                     "    tb_musical m ON s.MUSICAL_ID = m.MUSICAL_ID " +
                     "WHERE " +
                     "    r.USER_ID = ?"
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
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Database error occurred.\"}");
            return;
        }

        response.getWriter().write(events.toString());
    }
}
