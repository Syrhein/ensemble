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
            System.out.println("Retrieved Musical List: " + list); // 디버깅용 로그
        } finally {
            session.close();
        }
        return list;
    
    }
}


