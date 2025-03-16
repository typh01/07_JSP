package com.kh.ap.member.model.dto;

import java.sql.Date;

public class UserDTO {

	private Number UserNum = null;
	private String UserId = null;
	private String UserPw = null;
	private String UserName = null;
	private Date EnrollDate = null;
	
	public UserDTO() {
		super();
	}

	public UserDTO(Number userNum, String userId, String userPw, String userName, Date enrollDate) {
		super();
		UserNum = userNum;
		UserId = userId;
		UserPw = userPw;
		UserName = userName;
		EnrollDate = enrollDate;
	}

	public Number getUserNum() {
		return UserNum;
	}

	public void setUserNum(Number userNum) {
		UserNum = userNum;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getUserPw() {
		return UserPw;
	}

	public void setUserPw(String userPw) {
		UserPw = userPw;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public Date getEnrollDate() {
		return EnrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		EnrollDate = enrollDate;
	}
	
	@Override
	public String toString() {
		return "UserDTO [UserNum=" + UserNum + ", UserId=" + UserId + ", UserPw=" + UserPw + ", UserName=" + UserName
				+ ", EnrollDate=" + EnrollDate + "]";
	}
}
