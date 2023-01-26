package com.sh.yespresso.member.model.service;

import java.sql.Connection;

import static com.sh.yespresso.common.JdbcTemplate.close;
import static com.sh.yespresso.common.JdbcTemplate.commit;
import static com.sh.yespresso.common.JdbcTemplate.getConnection;
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
	/**
	 * yeji end
	 */


	/**
	 * awon start
	 */
	public int updateMember(Member member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.UpdateMember(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
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


	/** * jooh end */

}
