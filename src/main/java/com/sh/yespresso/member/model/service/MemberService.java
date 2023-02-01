package com.sh.yespresso.member.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import static com.sh.yespresso.common.JdbcTemplate.close;
import static com.sh.yespresso.common.JdbcTemplate.getConnection;
import static com.sh.yespresso.common.JdbcTemplate.commit;
import static com.sh.yespresso.common.JdbcTemplate.rollback;

import com.sh.yespresso.member.model.dao.MemberDao;
import com.sh.yespresso.member.model.dto.Member;

public class MemberService {
	private MemberDao memberDao = new MemberDao();
	/**
	 * hj start
	 */
	/**
	 * hj end
	 */


	/**
	 * yeji start
	 */
	public List<Member> selectAllMember(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Member> members = memberDao.selectAllMember(conn, param);
		close(conn);
		return members;
	}
	
	public int selectTotalCount() {
		Connection conn = getConnection();
		int totalCount = memberDao.selectTotalCount(conn);
		close(conn);
		return totalCount;
	}
	
	public List<Member> searchMember(Map<String, String> param) {
		Connection conn = getConnection();
		List<Member> members = memberDao.searchMember(conn, param);
		close(conn);
		return members;
	}
	
	public int updateMemberRole(String memberId, String memberRole) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.updateMemberRole(conn, memberId, memberRole);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}
	
	public int deleteMember(String memberId) {
		int result = 0;
		// 1. Connection 객체 생성
		Connection conn = getConnection();
		try {
			// 2. dao 요청
			result = memberDao.deleteMember(conn, memberId);
			// 3. 트랜잭션 처리
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e; // controller 통보용
		} finally {
			// 4. Connection 객체 반환
			close(conn);
		}
		return result;
	}
	/**
	 * yeji end
	 */


	/**
	 * awon start
	 */
	public int updateMyAccount(Member member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.updateMyAccount(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	
	public int myPasswordUpdate(Member member) {
		Connection conn = getConnection();
		int result = 0;;
		try {
			result = memberDao.myPasswordUpdate(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	
	public int myAccountDelete(String memberId) {
		int result = 0;
		// 1. Connection객체 생성
		Connection conn = getConnection();
		try {
			// 2. dao 요청
			result = memberDao.myAccountDelete(conn, memberId);
			// 3. 트랜잭션 처리
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e; // controller 통보용
		} finally {
			// 4. Connection객체 반환
			close(conn);
		}
		return result;
	}
	/**
	 * awon end
	 */


	/** * jooh start */
	
	public Member selectOneMember(String memberId) {
		//1. Connection 생성
		Connection conn  = getConnection();
		
		//2. Dao 요청 (Connection 전달)
		Member member = memberDao.selectOneMember(conn, memberId);
		
		//3. 반환
		close(conn);
		return member;
	}


	public int insertMember(Member member) {
		int result = 0;
		//Connection생성
		Connection conn = getConnection();
		//dao요청
		try {
			result = memberDao.insertMember(conn,member);
			// 트랜잭션 처리
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}


	/** * jooh end */

}
