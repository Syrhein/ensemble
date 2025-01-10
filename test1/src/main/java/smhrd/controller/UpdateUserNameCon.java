package smhrd.controller;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

import smhrd.model.Member;
import smhrd.model.MemberDAO;

@WebServlet("/UpdateUserNameCon")
public class UpdateUserNameCon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject jsonResponse = new JSONObject();

        try {
            // JSON 요청 본문 파싱
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = request.getReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }

            // JSON 파싱
            JSONObject jsonRequest = new JSONObject(sb.toString());
            String userId = jsonRequest.optString("userId");
            String newName = jsonRequest.optString("userName");

            // 파라미터 검증
            if (newName == null || newName.isEmpty()) {
                jsonResponse.put("success", false);
                jsonResponse.put("error", "변경할 이름을 입력해주세요.");
                response.getWriter().write(jsonResponse.toString());
                return;
            }

            // 세션 검증
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("info") == null) {
                jsonResponse.put("success", false);
                jsonResponse.put("error", "로그인 정보가 없습니다.");
                response.getWriter().write(jsonResponse.toString());
                return;
            }

            // 사용자 인증
            Member member = (Member) session.getAttribute("info");
            if (!member.getUserId().equals(userId)) {
                jsonResponse.put("success", false);
                jsonResponse.put("error", "잘못된 요청입니다.");
                response.getWriter().write(jsonResponse.toString());
                return;
            }

            // DAO 호출 및 처리
            MemberDAO dao = new MemberDAO();
            boolean updateSuccess = dao.updateUserName(userId, newName);

            if (updateSuccess) {
                member.setUserName(newName); // 세션 정보 업데이트
                session.setAttribute("info", member);
                jsonResponse.put("success", true);
            } else {
                jsonResponse.put("success", false);
                jsonResponse.put("error", "데이터베이스 업데이트에 실패했습니다.");
            }
        } catch (Exception e) {
            jsonResponse.put("success", false);
            jsonResponse.put("error", "서버 오류가 발생했습니다.");
            e.printStackTrace();
        }

        response.getWriter().write(jsonResponse.toString());
    }
}

