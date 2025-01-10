package smhrd.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import smhrd.db.SqlSessionManager;

import java.util.List;

public class BoardDAO {
	SqlSessionFactory factory = SqlSessionManager.getSqlSession();

	// 게시글 목록 가져오기
	public List<BoardVO> getBoardList() {
		SqlSession session = factory.openSession(true);
		List<BoardVO> list = null;
		try {
			list = session.selectList("MusicalMapper.getBoardList");
		} finally {
			session.close();
		}
		return list;
	}

	// 게시글 상세보기
	public BoardVO getBoardDetail(int postIdx) {
		SqlSession session = factory.openSession(true);
		BoardVO vo = null;
		try {
			// 조회수 증가 (옵션)
			incrementViewCount(postIdx); // 조회수 증가 먼저 호출
			vo = session.selectOne("MusicalMapper.getBoardDetail", postIdx);
		} finally {
			session.close();
		}
		return vo;
	}

	// 게시글 등록
	public int insertBoard(BoardVO vo) {
		SqlSession session = factory.openSession(true);
		int result = 0;
		try {
			result = session.insert("MusicalMapper.insertBoard", vo);
		} finally {
			session.close();
		}
		return result;
	}

	// 조회수 증가
	public void incrementViewCount(int postIdx) {
		SqlSession session = factory.openSession(true);
		try {
			session.update("MusicalMapper.incrementViewCount", postIdx);
		} finally {
			session.close();
		}
	}

	// 좋아요 증가
	// 좋아요 증가
	public void incrementPostLikes(int postIdx) {
		SqlSession session = factory.openSession(true);
		try {
			session.update("MusicalMapper.incrementPostLikes", postIdx);
		} finally {
			session.close();
		}
	}

//댓글 작성
	public int insertComment(CommentVO comment) {
		SqlSession session = factory.openSession(true);
		int result = 0;
		try {
			result = session.insert("MusicalMapper.insertComment", comment);
		} finally {
			session.close();
		}
		return result;
	}
	// 댓글 가져오기
	public List<CommentVO> getComments(int postIdx) {
	    SqlSession session = factory.openSession(true);
	    List<CommentVO> comments = null;
	    try {
	        comments = session.selectList("MusicalMapper.getComments", postIdx);
	    } finally {
	        session.close();
	    }
	    return comments;
	}

	// 댓글 좋아요 증가
	public int incrementCommentLikes(int cmtIdx) {
	    SqlSession session = factory.openSession(true);
	    int result = 0;
	    try {
	        result = session.update("MusicalMapper.incrementCommentLikes", cmtIdx);
	    } finally {
	        session.close();
	    }
	    return result;
	}

}
