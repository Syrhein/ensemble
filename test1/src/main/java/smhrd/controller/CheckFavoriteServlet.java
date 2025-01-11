package smhrd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import smhrd.model.FavoriteVO;
import smhrd.model.MusicalDAO;
@WebServlet("/CheckFavoriteServlet")
public class CheckFavoriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");

        String userId = request.getParameter("userId");
        String showIdxStr = request.getParameter("showIdx");

        if (userId == null || showIdxStr == null || userId.isEmpty() || showIdxStr.isEmpty()) {
            response.getWriter().write("{\"isFavorite\": false}");
            return;
        }

        try {
            int showIdx = Integer.parseInt(showIdxStr);
            FavoriteVO favorite = new FavoriteVO(userId, showIdx);

            MusicalDAO dao = new MusicalDAO();
            boolean isFavorite = dao.isFavoriteExists(favorite);

            response.getWriter().write("{\"isFavorite\": " + isFavorite + "}");
        } catch (NumberFormatException e) {
            response.getWriter().write("{\"isFavorite\": false}");
        }
    }
}
