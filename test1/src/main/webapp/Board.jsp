<%@ page import="java.util.List" %>
<%@ page import="smhrd.model.BoardDAO" %>
<%@ page import="smhrd.model.BoardVO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판</title>
    <link rel="stylesheet" href="./css/styles.css">
</head>
<body>
    <header>
        <div class="logo">Curtain Call Guide</div>
        <nav>
            <ul>
                <li><a href="Main.html">홈</a></li>
                <li><a href="#">국내창작</a></li>
                <li><a href="#">라이센스</a></li>
                <li><a href="Board.jsp">게시판</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <section class="board">
            <h1>게시판</h1>
            <div class="board-table">
                <table>
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            // DAO에서 게시글 목록 가져오기
                            BoardDAO dao = new BoardDAO();
                            List<BoardVO> boardList = dao.getBoardList();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                            if (boardList != null && !boardList.isEmpty()) {
                                for (BoardVO board : boardList) {
                        %>
                        <tr>
                            <td><%= board.getId() %></td>
                            <td><a href="BoardDetailCon?id=<%= board.getId() %>"><%= board.getTitle() %></a></td>
                            <td><%= board.getWriter() %></td>
                            <td>
                                <% if (board.getCreatedAt() != null) { %>
                                    <%= dateFormat.format(board.getCreatedAt()) %>
                                <% } else { %>
                                    N/A
                                <% } %>
                            </td>
                            <td><%= board.getViews() %></td>
                        </tr>
                        <% 
                                }
                            } else { 
                        %>
                        <tr>
                            <td colspan="5">게시글이 없습니다.</td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
            <div class="board-actions">
                <a href="BoardWrite.jsp" class="btn">글쓰기</a>
            </div>
        </section>
    </main>

    <footer>
        <p>&copy; 2025 Curtain Call Guide. 모든 권리 보유.</p>
    </footer>
</body>
</html>
