package com.kh.mfw.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mfw.member.model.dto.MemberDTO;
import com.kh.mfw.member.model.service.MemberService;

@WebServlet("/updateInfo")
public class UpdateInfoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateInfoController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 세션에서 로그인된 사용자 정보를 가져오기
        MemberDTO member = (MemberDTO) request.getSession().getAttribute("loginMember");

        // 파라미터에서 수정할 사용자 정보 가져오기
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");

        // 수정할 정보 업데이트
        member.setMemberName(userName);
        member.setEmail(userEmail);

        // MemberService를 통해 DB에서 정보 수정
        MemberService memberService = new MemberService();

        try {
            int result = memberService.updateInfo(member);

            if (result > 0) {
                // 정보 수정 성공 시 세션에 갱신된 정보를 저장
                request.getSession().setAttribute("loginMember", member);

                // myPage로 리다이렉트
                response.sendRedirect(request.getContextPath() + "/myPage");
            } else {
                // 수정 실패 시 에러 메시지 전달
                request.setAttribute("errorMessage", "정보 수정에 실패했습니다.");
                request.getRequestDispatcher("/myPage").forward(request, response);
            }
        } catch (Exception e) {
            // 예외 발생 시 에러 메시지 처리
            e.printStackTrace();
            request.setAttribute("errorMessage", "서버 오류가 발생했습니다.");
            request.getRequestDispatcher("/myPage").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
