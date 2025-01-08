package smhrd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import smhrd.model.Member;
import smhrd.model.MemberDAO;



@WebServlet("/JoinCon")
public class JoinCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String nick = request.getParameter("nick");
		String email = request.getParameter("email");
		String tell = request.getParameter("tell");
		
		Member vo = new Member();
		vo.setId(id);
		vo.setPw(pw);
		vo.setNick(nick);
		vo.setEmail(email);
		vo.setTell(tell);
		
		
		MemberDAO dao = new MemberDAO();
		int result = dao.join(vo);
		
		
		if(result > 0) {
			RequestDispatcher rd = request.getRequestDispatcher("Main.html");
			rd.forward(request, response);
			
		}else {
			response.sendRedirect("signUp.html");
		}
		
		
		
	}

}
