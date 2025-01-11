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

    // 특정 뮤지컬의 공연 정보 가져오기
    public MusicalDetailVO getMusicalDetails(String musicalId) {
        try (SqlSession session = factory.openSession(true)) {
            return session.selectOne("MusicalMapper.getMusicalDetails", musicalId);
        }
    }
    
    // 특정 musicalId로 showIdx 가져오기
    public String getShowIdxByMusicalId(String musicalId) {
        try (SqlSession session = factory.openSession(true)) {
            return session.selectOne("MusicalMapper.getShowIdxByMusicalId", musicalId);
        }
    }

    // 상세정보 조회수 증가
    public boolean incrementMusicalViews(String musicalId) {
        try (SqlSession session = factory.openSession(true)) {
            int result = session.update("MusicalMapper.incrementMusicalViews", musicalId);
            return result > 0; // 업데이트된 행 수가 1 이상이면 성공
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
   
    // 관심 등록 여부 확인
    public boolean isFavoriteExists(FavoriteVO favorite) {
        SqlSession session = factory.openSession(true);
        try {
            int count = session.selectOne("MusicalMapper.isFavoriteExists", favorite);
            return count > 0; // 관심 등록 여부 반환
        } finally {
            session.close();
        }
    }

    // 관심 등록
    public boolean addFavorite(FavoriteVO favorite) {
        SqlSession session = factory.openSession(true);
        try {
            int result = session.insert("MusicalMapper.addFavorite", favorite);
            return result > 0; // 삽입 성공 여부 반환
        } finally {
            session.close();
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
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // 리뷰 등록
    public int insertReview(ReviewVO review) {
        try (SqlSession session = factory.openSession(true)) {
            return session.insert("MusicalMapper.insertReview", review);
        }
    }
}
