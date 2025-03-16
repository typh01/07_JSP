package com.kh.mfw.member.model.service;

import org.apache.ibatis.session.SqlSession;

import static com.kh.mfw.common.Template.getSqlSession;

import com.kh.mfw.member.model.dao.MemberDAO;
import com.kh.mfw.member.model.dto.MemberDTO;

public class MemberService {
	
	public MemberDTO login(MemberDTO member) {
		
		// JDBCUtil 클래스로부터
		// static Method로 구현해놓은
		// getConnection 메서드를 호출하여
		// Connection 객체를 반환받았단 말이죠?
		SqlSession sqlSession = getSqlSession(); // 참조 연산자 이용 / 직접 접근 연산자
		
		// 유효성 검증 - 하지만 여기선 안함 나중에 함
		
		//DAO 객체와의 상호작용(호출)
		MemberDTO loginMember = new MemberDAO().login(sqlSession, member); // 값이 저장된 주소 값 (참조한다)
		
		sqlSession.close();
		
		return loginMember;
	}
	
	
	// 의사 결정 코드
	public int signUp(MemberDTO member) {
		// 3차 유효성 검증 (Java)
		// 4차 데이터 무결성을 위한 제약조건(DBMS)
		// 아이디 중복 검사
		
		SqlSession sqlSession = getSqlSession();
		
		//boolean result = new MemberDAO().checkId(sqlSession, member.getMemberId());
		
		if(new MemberDAO().checkId(sqlSession, member.getMemberId())) {
			sqlSession.close();
			return 0;
		}
		
		int result = new MemberDAO().signUp(sqlSession, member);
		sqlSession.commit();
		sqlSession.close();
		
		return result;
	}
	
}
