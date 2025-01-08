package smhrd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import smhrd.model.Member;
import smhrd.model.MemberDAO;

@WebServlet("/LoginCon")
public class LoginCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		MemberDAO dao = new MemberDAO();
		Member vo = new Member();
		vo.setId(id);
		vo.setPw(pw);
		Member result = dao.login(vo); 
		if(result!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("info", result);
			session.setAttribute("user", result.getNick());
			RequestDispatcher dispatcher = request.getRequestDispatcher("Main.html");
			dispatcher.forward(request, response);
		}else {
		response.sendRedirect("login.html");
		}
		
	}

}
