package com.kh.mfw.member.controller;

import com.kh.mfw.member.model.service.MemberService;
import com.kh.mfw.member.model.dto.MemberDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteMember")
public class DeleteMemberController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MemberService memberService = new MemberService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 세션 정보 가져오기 (로그인 상태 확인)
        HttpSession session = request.getSession();
        MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");

        // 로그인 상태가 아니면 로그인 페이지로 리다이렉트
        if (loginMember == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String memberId = loginMember.getMemberId();
        
        // 바로 회원 탈퇴 처리
        int result = memberService.deleteMember(memberId);

        if (result > 0) {
            // 탈퇴 성공 후 로그아웃 처리
            session.invalidate(); // 세션 무효화
            response.sendRedirect(request.getContextPath() + "/"); // 메인 페이지로 리다이렉트
        } else {
            // 탈퇴 실패
            System.out.println("탈퇴 실패!");
            request.getRequestDispatcher("/myPage").forward(request, response);
        }
    }
}