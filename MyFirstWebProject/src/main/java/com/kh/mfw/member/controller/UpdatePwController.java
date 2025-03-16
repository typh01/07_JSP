package com.kh.mfw.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mfw.member.model.dto.MemberDTO;
import com.kh.mfw.member.model.service.MemberService;

@WebServlet("/updatePw")
public class UpdatePwController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdatePwController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 세션에서 로그인된 사용자 정보 가져오기
		String userId = ((MemberDTO) request.getSession().getAttribute("loginMember")).getMemberId();
        String userPwd = request.getParameter("userPwd"); // 현재 비밀번호
        String changePwd = request.getParameter("changePwd"); // 변경할 비밀번호

        // MemberDTO 객체 생성하여 로그인 처리
        MemberDTO member = new MemberDTO();
        member.setMemberId(userId);
        member.setMemberPw(userPwd);

        try {
            // MemberService 인스턴스 생성
            MemberService memberService = new MemberService();
            
            // 비밀번호 확인 (현재 비밀번호가 일치하는지 확인)
            MemberDTO loginMember = memberService.login(member);

            if (loginMember != null) {  // 현재 비밀번호가 일치하면
                // 비밀번호 변경
                int result = memberService.updatePw(userId, changePwd);

                if (result > 0) {  // 비밀번호 변경 성공
                    // 비밀번호 변경 성공 후 리다이렉트
                    response.sendRedirect(request.getContextPath() + "/myPage"); // 예시: 마이페이지로 리다이렉트
                } else {
                    // 비밀번호 변경 실패 메시지
                    request.setAttribute("error", "비밀번호 변경에 실패했습니다.");
                    request.getRequestDispatcher("/myPage").forward(request, response);
                }
            } else {  // 현재 비밀번호가 틀린 경우
                // 비밀번호 오류 메시지
                request.setAttribute("error", "현재 비밀번호가 일치하지 않습니다.");
                request.getRequestDispatcher("/myPage").forward(request, response);
            }
        } catch (Exception e) {
            // 예외 발생 시 처리 (로그를 남기거나 에러 메시지를 보여줄 수 있음)
            e.printStackTrace();  // 예외 로그 출력
            request.setAttribute("error", "비밀번호 변경 중 오류가 발생했습니다.");
            request.getRequestDispatcher("/myPage").forward(request, response);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
