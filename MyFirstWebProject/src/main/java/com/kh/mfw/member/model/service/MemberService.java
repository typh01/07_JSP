package com.kh.mfw.member.model.service;

import com.kh.mfw.member.model.dao.MemberDAO;
import com.kh.mfw.member.model.dto.MemberDTO;

public class MemberService {
	
	public MemberDTO login(MemberDTO member) {
		
		/* 
		 * 유효성 검사 (Validation)
		 * member.getMemberId().length() > 10;
		 * member.getMemberPw()!.matches("/^[A-Za-z0-1]");
		 * member.getMemberId().equals(""); member.getMemberPw().equals("");
		 *
		 * DB 갈 필요 없는거 아님?
		 * 서비스단에서 유효성검사하기(Validation)
		 */
		
		 MemberDTO loginMember = new MemberDAO().login(member);
		 return loginMember;
	}

	public int signUp(MemberDTO member) {
		
		int result = new MemberDAO().checkId(member.getMemberId());
		
		if(result > 0) {
			return result;
		} 
		
		new MemberDAO().signUp(member);
		return result;
	}
	
	 private MemberDAO memberDAO = new MemberDAO();

	    public int updateInfo(MemberDTO member) {
	        return memberDAO.updateInfo(member);
	    }

	    public int updatePw(String memberId, String newPw) {
	        return memberDAO.updatePw(memberId, newPw);
	    }
	
	    public int deleteMember(String memberId) {
	        return memberDAO.deleteMember(memberId);
	    }
	    
}
