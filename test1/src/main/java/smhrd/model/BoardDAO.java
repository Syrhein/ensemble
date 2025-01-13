package smhrd.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import smhrd.db.SqlSessionManager;

import java.util.List;

public class BoardDAO {
    SqlSessionFactory factory = SqlSessionManager.getSqlSession();

    // 게시글 목록 가져오기
    public List<BoardVO> getBoardList() {
        try (SqlSession session = factory.openSession(true)) {
            return session.selectList("MusicalMapper.getBoardList");
        }
    }

    // 게시글 상세보기 (조회수 증가 없이)
    public BoardVO getBoardDetail(int postIdx) {
        try (SqlSession session = factory.openSession(true)) {
            return session.selectOne("MusicalMapper.getBoardDetail", postIdx);
        }
    }

    // 조회수 증가
    public void incrementViewCount(int postIdx) {
        try (SqlSession session = factory.openSession(true)) {
            session.update("MusicalMapper.incrementViewCount", postIdx);
        }
    }

    // 게시글 등록
    public int insertBoard(BoardVO vo) {
        try (SqlSession session = factory.openSession(true)) {
            return session.insert("MusicalMapper.insertBoard", vo);
        }
    }

    // 좋아요 증가
    public int incrementPostLikes(int postIdx) {
        int updatedLikes = 0;

        try (SqlSession session = factory.openSession()) {
            try {
                // 좋아요 증가 처리
                int rowsAffected = session.update("MusicalMapper.incrementPostLikes", postIdx);

                if (rowsAffected > 0) {
                    // 업데이트 성공 시 좋아요 수 조회
                    updatedLikes = session.selectOne("MusicalMapper.getPostLikes", postIdx);
                    session.commit(); // 성공 시 트랜잭션 커밋
                } else {
                    throw new Exception("Post not found with postIdx: " + postIdx);
                }
            } catch (Exception e) {
                session.rollback(); // 실패 시 트랜잭션 롤백
                e.printStackTrace();
                throw new RuntimeException("An error occurred while updating post likes.", e);
            }
        }

        return updatedLikes;
    }
    
    // 게시글 목록 가져오기
    public List<BoardVO> getPostList(String userId) {
        SqlSession session = factory.openSession(true); // Auto-commit 설정
        List<BoardVO> list = null;
        try {
            list = session.selectList("MusicalMapper.getPostList", userId);
        } finally {
            session.close();
        }
        return list;
    }




    // 댓글 작성
    public int insertComment(CommentVO comment) {
        try (SqlSession session = factory.openSession(true)) {
            return session.insert("MusicalMapper.insertComment", comment);
        }
    }

    // 댓글 가져오기
    public List<CommentVO> getComments(int postIdx) {
        try (SqlSession session = factory.openSession(true)) {
            return session.selectList("MusicalMapper.getComments", postIdx);
        }
    }

    // 댓글 좋아요 증가
    public int incrementCommentLikes(int cmtIdx) {
        try (SqlSession session = factory.openSession(true)) {
            return session.update("MusicalMapper.incrementCommentLikes", cmtIdx);
        }
    }
}
