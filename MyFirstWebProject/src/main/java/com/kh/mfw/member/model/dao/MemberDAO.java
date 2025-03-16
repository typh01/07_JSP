package com.kh.mfw.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.mfw.member.model.dto.MemberDTO;

public class MemberDAO {
	
	//DB와의 직접적인 상호작용(영속성 작업)
	static {
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
			// 패키지 경로부터 클래스 이름까지(FullClassName)
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public MemberDTO login(MemberDTO member) {
			
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
			
			String sql ="""
						SELECT
							  MEMBER_ID
							, MEMBER_PW
							, MEMBER_NAME
							, EMAIL
							, ENROLL_DATE
						 FROM
						 	  KH_MEMBER
						WHERE
						 	  MEMBER_ID = ?
						  AND
						  	  MEMBER_PW = ?
					""";
			
			MemberDTO loginMember = null;
			
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@112.221.156.34:12345:XE",
											   "KH12_PTH",
											   "KH1234");
			
			pstmt = conn.prepareStatement(sql); 
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				// 지역변수 { } 단위
				loginMember = new MemberDTO(
											rset.getString("MEMBER_ID"),
											rset.getString("MEMBER_PW"),
											rset.getString("MEMBER_NAME"),
											rset.getString("EMAIL"),
											rset.getDate("ENROLL_DATE")
											);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(rset != null) rset.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(pstmt != null) pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return loginMember;
	}
	
	
	public int checkId(String memberId) {
		
		String sql ="""
						SELECT
								COUNT(*)
						  FROM
						  	    KH_MEMBER
						  WHERE
						  		MEMBER_ID = ?
					"""; 
		// COUNT(*) - 무조건 1 or 0행  / column - 반환되는 만큼
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		try {
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@112.221.156.34:12345:XE",
					   "KH12_PTH",
					   "KH1234");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			
			/*
			 * case 1 : count(*) 그룹함수를 사용했을 때
			 * 			무조건 ResultSet이 1행이 존재함
			 * 			컬럼값이 0 / 1인것으로 조회결과 판별
			 * 
			 * rset.next();
			 * return rset.getInt("COUNT(*)");
			 */
			
			/*
			 * case 2 : MEMBER_ID 컬럼을 조회한 경우
			 * 
			 * return pstmt.excuteQuery().next();
			 */
			rset = pstmt.executeQuery();
			rset.next();
			result = rset.getInt("COUNT(*)");
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(rset != null) rset.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(pstmt != null) pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


	public void signUp(MemberDTO member) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = """
						INSERT 
						  INTO
						  		KH_MEMBER
						VALUES
								(
								?
							 ,	?
							 ,	?
							 ,	?
							 ,	DEFAULT
							 	)
					 """;
		
		try {
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@112.221.156.34:12345:XE",
											   "KH12_PTH",
											   "KH1234");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getEmail());
			
			pstmt.executeUpdate();
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(pstmt != null) pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

    public int updateInfo(MemberDTO member) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        String sql = """
        		UPDATE 
        				KH_MEMBER 
        		   SET 
        		   		MEMBER_PW = ?
        		   	 ,  MEMBER_NAME = ?
        		   	 ,  EMAIL = ? 
        		 WHERE 
        		 		MEMBER_ID = ?
        		""";

        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@112.221.156.34:12345:XE", "KH12_PTH", "KH1234");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getMemberPw());
            pstmt.setString(2, member.getMemberName());
            pstmt.setString(3, member.getEmail());
            pstmt.setString(4, member.getMemberId());

            result = pstmt.executeUpdate();
           

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, null);
        }

        return result;
    }

    public int updatePw(String memberId, String newPw) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        String sql = """
        		UPDATE 
        				KH_MEMBER 
        		   SET  
        		   		MEMBER_PW = ? 
        		 WHERE  
        		 		MEMBER_ID = ?
        		""";

        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@112.221.156.34:12345:XE", "KH12_PTH", "KH1234");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPw);
            pstmt.setString(2, memberId);

            result = pstmt.executeUpdate();
            
            if (result > 0) {
                conn.commit();
            } else {
                conn.rollback();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, null);
        }

        return result;
    }
    
    public int deleteMember(String memberId) {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	int result = 0;
    	
    	String sql = "DELETE FROM KH_MEMBER WHERE MEMBER_ID = ?";
    	
    	try {
    		conn = DriverManager.getConnection("jdbc:oracle:thin:@112.221.156.34:12345:XE", "KH12_PTH", "KH1234");
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, memberId);
    		
    		result = pstmt.executeUpdate();
            
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		close(conn, pstmt, null);
    	}
    	
    	return result;
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rset) {
        try {
            if (rset != null) rset.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}