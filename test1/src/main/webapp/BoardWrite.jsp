<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 작성</title>
    <link rel="stylesheet" href="./css/styles.css">
</head>
<body>
    <header>
        <h1>게시글 작성</h1>
    </header>
    <main>
        <form action="BoardWriteCon" method="post" enctype="multipart/form-data">
            <div>
                <label for="title">제목</label>
                <input type="text" id="title" name="postTitle" required>
            </div>
            <div>
                <label for="content">내용</label>
                <textarea id="content" name="postContent" rows="10" required></textarea>
            </div>
            <div>
                <label for="file">파일 첨부</label>
                <input type="file" id="file" name="file">
            </div>
            <div>
                <button type="submit">작성 완료</button>
                <a href="Board.jsp">취소</a>
            </div>
        </form>
    </main>
</body>
</html>
