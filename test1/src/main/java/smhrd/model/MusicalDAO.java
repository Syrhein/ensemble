package smhrd.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import smhrd.db.SqlSessionManager;

import java.util.List;

public class MusicalDAO {
    SqlSessionFactory factory = SqlSessionManager.getSqlSession();

    // 뮤지컬 목록 가져오기
    public List<MusicalVO> getMusicalList() {
        SqlSession session = factory.openSession(true); // Auto-commit 설정
        List<MusicalVO> list = null;
        try {
            list = session.selectList("MusicalMapper.getMusicalList");
        } finally {
            session.close();
        }
        return list;
    }

    // 특정 뮤지컬 상세 정보 가져오기
    public MusicalVO getMusicalById(String musicalId) {
        try (SqlSession session = factory.openSession(true)) {
            return session.selectOne("MusicalMapper.getMusicalById", musicalId);
        }
    }

    // 특정 뮤지컬의 공연 정보 가져오기
    public MusicalDetailVO getMusicalDetails(String musicalId) {
        try (SqlSession session = factory.openSession(true)) {
            return session.selectOne("MusicalMapper.getMusicalDetails", musicalId);
        }
    }
    
    // 상세정보 조회수
    public boolean incrementMusicalViews(String musicalId) {
        try (SqlSession session = factory.openSession(true)) {
            int result = session.update("MusicalMapper.incrementMusicalViews", musicalId);
            return result > 0; // 업데이트된 행 수가 1 이상이면 성공
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //리뷰 -- 수정
   public int insertReview(ReviewVO review) {
	   try (SqlSession session = factory.openSession(true)) {
		   return session.insert("MusicalMapper.insertReview", review);
       }
	   
   }
   
   // 상위 5개 뮤지컬 정보 가져오기
   public List<MusicalVO> getTopMusicals() {
	    SqlSession session = factory.openSession(true);
	    List<MusicalVO> topMusicals = null;
	    try {
	        topMusicals = session.selectList("MusicalMapper.getTopMusicalPosters");
	    } finally {
	        session.close();
	    }
	    return topMusicals;
	}
}
