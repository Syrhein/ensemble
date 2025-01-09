<%@page import="smhrd.model.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="smhrd.model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                <li><a href="cate_create.html">국내창작</a></li>
                <li><a href="cate_license.html">라이센스</a></li>
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
                            <th>좋아요</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            BoardDAO dao = new BoardDAO();
                            List<BoardVO> boardList = dao.getBoardList();
                            request.setAttribute("boardList", boardList);
                        %>
                        <c:forEach var="board" items="${boardList}">
                            <tr>
                                <td>${board.postIdx}</td>
                                <td><a href="BoardDetailCon?postIdx=${board.postIdx}">${board.postTitle}</a></td>
                                <td>${board.userId}</td>
                                <td>${board.getCreatedAt()}</td>
                                <td>${board.postViews}</td>
                                <td>${board.postLikes}</td>
                            </tr>
                        </c:forEach>
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
