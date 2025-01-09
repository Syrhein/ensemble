package smhrd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CheckLoginCon")
public class CheckLoginCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("info");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (user != null) {
            response.getWriter().write("{\"isLoggedIn\": true}");
        } else {
            response.getWriter().write("{\"isLoggedIn\": false}");
        }
		
	}

}
