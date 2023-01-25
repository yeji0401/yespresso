package com.sh.yespresso.member.model.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class Member {
	private String memberId; //아이디
	private MemberRole memberRole; //회원등급
	private String password; //비밀번호
	private String memberName; //회원명
	private Date birthday; //생일
	private Gender gender; //성별
	private String email; //이메일
	private String phone; //전화번호
	private String address; //주소
	private Timestamp enrollDate; //가입일
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(String memberId, MemberRole memberRole, String password, String memberName, Date birthday,
			Gender gender, String email, String phone, String address, Timestamp enrollDate) {
		super();
		this.memberId = memberId;
		this.memberRole = memberRole;
		this.password = password;
		this.memberName = memberName;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.enrollDate = enrollDate;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public MemberRole getMemberRole() {
		return memberRole;
	}
	public void setMemberRole(MemberRole memberRole) {
		this.memberRole = memberRole;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Timestamp getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Timestamp enrollDate) {
		this.enrollDate = enrollDate;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberRole=" + memberRole + ", password=" + password
				+ ", memberName=" + memberName + ", birthday=" + birthday + ", gender=" + gender + ", email=" + email
				+ ", phone=" + phone + ", address=" + address + ", enrollDate=" + enrollDate + "]";
	}
	
	
}
