package tnt4.service;

import java.util.List;

import tnt4.container.Container;
import tnt4.dao.MemberDao;
import tnt4.dto.Member;

public class MemberService {
	private MemberDao memberDao;

	public MemberService() {
		memberDao = Container.memberDao;
	}

	// DB Data에 있는 ID 가져옴
	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	// DB Data에 있는 PW 가져옴
	public Member getMemberByLoginPw(String loginPw) {
		return memberDao.getMemberByLoginPw(loginPw);
	}

	public Member getMember(int memberId) {
		return memberDao.getMember(memberId);
	}

	public void changeNewPw(String loginPw, String loginId) {
		memberDao.changePw(loginPw, loginId);
	}

	public void join(String loginId, String loginPw, String name, String gender, int height, int weight, int bmiId) {
		Member member = new Member(loginId, loginPw, name, gender, height, weight, bmiId);
		memberDao.join(member);
	}

	public void modify(int bmiId, String name, String gender, int height, int weight, String loginedId) {
//		Member member = new Member(name, gender, height, weight, bmiId);
		memberDao.modify(bmiId, name, gender, height, weight, loginedId);
	}

	public void delete(Member member) {
		memberDao.delete(member);
	}

	public List<String> getMemberLog(String userName) {
		return memberDao.getMemberLogByLoginID(userName);

	}
}
