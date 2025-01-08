package smhrd.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import smhrd.db.SqlSessionManager;

import java.util.List;

public class BoardDAO {
    SqlSessionFactory factory = SqlSessionManager.getSqlSession();

    // 조회수 증가
    public void incrementViewCount(int postIdx) {
        SqlSession session = factory.openSession(true); // Auto-commit 설정
        try {
            session.update("MusicalMapper.incrementViewCount", postIdx);
        } finally {
            session.close();
        }
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

    // 게시글 목록 가져오기
    public List<BoardVO> getBoardList() {
        SqlSession session = factory.openSession(true);
        List<BoardVO> list = null;
        try {
            list = session.selectList("MusicalMapper.getBoardList");
            System.out.println("Retrieved Board List: " + list); // 디버깅용 로그
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
            vo = session.selectOne("MusicalMapper.getBoardDetail", postIdx);
            System.out.println("Retrieved Board: " + vo);
        } finally {
            session.close();
        }
        return vo;
    }
}
