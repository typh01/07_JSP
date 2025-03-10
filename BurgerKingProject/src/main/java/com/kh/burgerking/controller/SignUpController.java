package com.kh.burgerking.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.burgerking.model.dto.UserDTO;

@WebServlet("/sign-up.do")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignUpController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("POST에서 나를 불렀움!");
		
		/*
		 * 컨트롤러
		 * 
		 * 1. request 객체로부터 값을 뽑아서 DTO로 가공
		 * 2. 요청 처리 (Service로 전달해 처리)
		 * 3. VIEW 처리
		 */
		//POST방식은 인코딩 설정이 ISO-8859-1방식으로 바뀜
		// 1) 값 뽑기 전에 UTF-8방식으로 변경해야함!!!!!!!!!!!!!!!
		request.setCharacterEncoding("UTF-8");
		
		// 1) 값 뽑기
		// request.getParameter("input요소의 name 속성 값");
		String userId = request.getParameter("userId");
		System.out.println(userId);
		String userPw = request.getParameter("userPw");
		System.out.println(userPw);
		String userName = request.getParameter("userName");
		System.out.println(userName);
		
		UserDTO user = new UserDTO(userId, userPw, userName);
		
		// 2) service.insertMember(user); ~~~ 잘가 ~~~
		// 잘다녀왔다고 가정
		// Service -> DAO -> DB
		// : int
		
		// 3) VIEW 처리
		// Presentation layer 분리! (OrderServlete은 단일 책임 원칙에 걸림)
		
		// JSP를 이용해서 응답데이터 만들기
		
		// JSP : Java 기반의 서버 사이드 스크립트 언어
		// ASP, PHP (보통 서버에서 도는 친구들은 P로 끝남)
		
		// ---------------------------------------------------------
		// 응답화면(JSP)에서 필요한 데이터를 넘겨줄 것 -> request 에 담아서 JSP로 넘김
		
		// Attribute => 키 : 밸류 세트로 묶어서 값을 담을 수 있음
		request.setAttribute("user", user); // 속성(값을 담는 공간)
		request.setAttribute("message", "요청 처리에 성공했습니다!");
		// MAP과 유사함
		
		
		// 응답페이지를 JSP에게 위임(배정)
		
		// RequestDispatcher	(컴퓨터 공학에서 Dispatcher와 같은 의미)
		RequestDispatcher view = request.getRequestDispatcher("/views/response_page.jsp");
								// 요청을 처리할 JSP의 경로(webapp 부터 시작)와 이름
		// 입력값(ex. user)과 request 와 response 를 JSP로 전달해야함
		//JSP => Servlet 으로 결국 변환됨
		
		// view // request, response
		view.forward(request, response);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("진짜로 내가 호출됨?? 진짜? 가짜아님??");
		doGet(request, response);
	}

}
