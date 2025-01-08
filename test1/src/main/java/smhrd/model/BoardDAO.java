package smhrd.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import smhrd.db.SqlSessionManager;

import java.util.List;

public class BoardDAO {
    SqlSessionFactory factory = SqlSessionManager.getSqlSession();
    
    public void incrementViewCount(int id) {
        SqlSession session = factory.openSession(true); // Auto-commit 설정
        try {
            session.update("com.smhrd.db.BookAndBoardMapper.incrementViewCount", id);
        } finally {
            session.close();
        }
    }
    
    public int insertBoard(BoardVO vo) {
        SqlSession session = factory.openSession(true);
        int result = 0;
        try {
            result = session.insert("com.smhrd.db.BookAndBoardMapper.insertBoard", vo);
        } finally {
            session.close();
        }
        return result;
    }


    public List<BoardVO> getBoardList() {
        SqlSession session = factory.openSession(true);
        List<BoardVO> list = null;
        try {
            list = session.selectList("com.smhrd.db.BookAndBoardMapper.getBoardList");
            System.out.println("Retrieved Board List: " + list); // 디버깅용 로그
        } finally {
            session.close();
        }
        return list;
    }


    public BoardVO getBoardDetail(int id) {
        SqlSession session = factory.openSession(true);
        BoardVO vo = null;
        try {
            vo = session.selectOne("com.smhrd.db.BookAndBoardMapper.getBoardDetail", id);
            System.out.println("Retrieved Board: " + vo);
        } finally {
            session.close();
        }
        return vo;
    }

}
