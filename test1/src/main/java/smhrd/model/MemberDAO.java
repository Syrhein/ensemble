package smhrd.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import smhrd.db.SqlSessionManager;

public class MemberDAO {
    SqlSessionFactory factory = SqlSessionManager.getSqlSession();

    // 회원가입
    public int join(Member member) {
        SqlSession session = factory.openSession(true); // auto commit -> true 자동 커밋
        int result = 0;
        try {
            result = session.insert("MusicalMapper.join", member); // Mapper의 join ID를 호출
        } finally {
            session.close();
        }
        return result;
    }

    // 로그인
    public Member login(String userId, String userPw) {
        SqlSession session = factory.openSession(true); // auto commit -> true 자동 커밋
        Member result = null;
        try {
            // 매개변수를 Mapper에 전달
            Member loginParams = new Member();
            loginParams.setUserId(userId);
            loginParams.setUserPw(userPw);
            result = session.selectOne("MusicalMapper.login", loginParams); // Mapper의 login ID를 호출
        } finally {
            session.close();
        }
        return result;
    }

    // 회원 정보 수정
    public int updateProfile(Member member) {
        SqlSession session = factory.openSession(true); // auto commit -> true 자동 커밋
        int result = 0;
        try {
            result = session.update("MusicalMapper.updateProfile", member); // Mapper의 updateProfile ID를 호출
        } finally {
            session.close();
        }
        return result;
    }

    // 회원 탈퇴
    public int deleteMember(String userId) {
        SqlSession session = factory.openSession(true); // auto commit -> true 자동 커밋
        int result = 0;
        try {
            result = session.delete("MusicalMapper.deleteMember", userId); // Mapper의 deleteMember ID를 호출
        } finally {
            session.close();
        }
        return result;
    }
    
    // 이름바꾸기
    public boolean updateUserName(String userId, String newName) {
        try (SqlSession session = factory.openSession(true)) { // Auto-commit enabled
            int result = session.update("MusicalMapper.updateUserName", Map.of("userId", userId, "newName", newName));
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
