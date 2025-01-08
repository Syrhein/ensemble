package smhrd.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Clob;
import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import smhrd.model.BoardDAO;
import smhrd.model.BoardVO;

@WebServlet("/BoardWriteCon")
@MultipartConfig
public class BoardWriteCon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String uploadPath = getServletContext().getRealPath("/") + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        BoardVO vo = new BoardVO();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List<FileItem> formItems = upload.parseRequest(request);
            for (FileItem item : formItems) {
                if (item.isFormField()) {
                    if (item.getFieldName().equals("postTitle")) {
                        vo.setPostTitle(item.getString("UTF-8"));
                    }
                    if (item.getFieldName().equals("postContent")) {
                        // String 데이터를 Clob으로 변환
                        try {
                            Context initContext = new InitialContext();
                            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/YourDataSourceName");
                            Connection conn = ds.getConnection();

                            Clob contentClob = conn.createClob();
                            contentClob.setString(1, item.getString("UTF-8"));
                            vo.setPostContent(contentClob);

                            conn.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new ServletException("Error while setting Clob data for postContent");
                        }
                    }
                } else {
                    if (!item.getName().isEmpty()) {
                        String uniqueFileName = UUID.randomUUID().toString() + "_" + item.getName();
                        File uploadedFile = new File(uploadPath + File.separator + uniqueFileName);
                        item.write(uploadedFile);
                        vo.setPostFileName(uniqueFileName);
                        vo.setPostFilePath("uploads/" + uniqueFileName);
                    }
                }
            }

            HttpSession session = request.getSession();
            vo.setUserId((String) session.getAttribute("user"));
            if (vo.getUserId() == null) vo.setUserId("Anonymous");

            vo.setPostViews(0);
            vo.setPostLikes(0);

            BoardDAO dao = new BoardDAO();
            if (dao.insertBoard(vo) > 0) {
                response.sendRedirect("Board.jsp");
            } else {
                response.sendRedirect("Main.html");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Main.html");
        }
    }
}
