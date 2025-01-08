package smhrd.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
                    if (item.getFieldName().equals("title")) vo.setTitle(item.getString("UTF-8"));
                    if (item.getFieldName().equals("content")) vo.setContent(item.getString("UTF-8"));
                } else {
                    if (!item.getName().isEmpty()) {
                        String uniqueFileName = UUID.randomUUID().toString() + "_" + item.getName();
                        File uploadedFile = new File(uploadPath + File.separator + uniqueFileName);
                        item.write(uploadedFile);
                        vo.setFileName(uniqueFileName);
                        vo.setFilePath("uploads/" + uniqueFileName);
                    }
                }
            }

            HttpSession session = request.getSession();
            vo.setWriter((String) session.getAttribute("user"));
            if (vo.getWriter() == null) vo.setWriter("Anonymous");

            BoardDAO dao = new BoardDAO();
            if (dao.insertBoard(vo) > 0) response.sendRedirect("Board.jsp");
            else response.sendRedirect("Main.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Main.jsp");
        }
    }
}
