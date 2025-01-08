package smhrd.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import smhrd.db.SqlSessionManager;

public class MemberDAO {
	SqlSessionFactory factory = SqlSessionManager.getSqlSession();
	
	public int join(Member vo) {
		
		SqlSession session = factory.openSession(true); // auto commit -> true 자동 커밋
		
		
		int result = session.insert("join", vo);
		
		session.close();
		
		return result;
	}
	
	
	public Member login(Member vo) {
		SqlSession session = factory.openSession(true); // auto commit -> true 자동 커밋
		
		// 회원이 입력한 id,pw가 db에 있는 데이터 중 일치하는것이 있는지 확인 select
		// selectOne(xml과 연결고리 id값, 같이보내는 데이터)
		Member result = session.selectOne("login", vo);
		
		session.close();
		
		return result;
	}


}
