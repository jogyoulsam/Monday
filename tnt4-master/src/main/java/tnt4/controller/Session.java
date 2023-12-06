package tnt4.controller;

import tnt4.dto.Member;
// Session
// 현재 사용사가 이용중인 정보
// 이 안의 정보는 사용자가 프로그램을 사용 할 때 동안은 계속 유지됨.
public class Session {
	private Member loginedMember;
	/*	private String loginedMemberLoginedId;
		private String loginedMemberLoginedPw;
		private String loginedMemberLoginedName;
		private String loginedMemberLoginedGender;
		private int loginedMemberLoginedHeight;
		private int loginedMemberLoginedWeight;
		private int loginedMemberLoginedBmiId; */
	
	public Member getLoginedMember() {
		return loginedMember;
	}
	public void setLoginedMember(Member loginedMember) {
		this.loginedMember = loginedMember;
	}
	public boolean isLogined() {
		return loginedMember != null;
	}
}
