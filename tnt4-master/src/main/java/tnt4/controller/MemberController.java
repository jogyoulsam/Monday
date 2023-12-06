package tnt4.controller;

import java.util.List;
import java.util.Scanner;

import tnt4.container.Container;
import tnt4.dto.Member;
import tnt4.service.MemberService;

public class MemberController extends Controller {
	private static Scanner sc;
	private static MemberService memberService;
	private static Session session;

	public MemberController() {
		sc = Container.getScanner();
		session = Container.getSession();
		memberService = Container.memberService;
	}

	public void doAction(String command, String loginId) {
		switch (command) {
		case "member join":
			doJoin();
			break;
		case "member login":
			doLogin();
			break;
		case "member logout":
			doLogout();
			break;
		case "member info":
			showInfo();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다. (member)");
			break;
		}
	}

	// 로그인 상태 확인
	private static boolean isJoinableLoginId(String loginId) {
		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return true;
		}
		return false;
	}

	// 회원가입
	public static void doJoin() {
		System.out.println();
		String loginId = null;

		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (isJoinableLoginId(loginId) == false) {
				System.out.printf("%s(은)는 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}
			break;
		}
		String loginPw = null;
		String loginPwConfirm = null;
		while (true) {
			System.out.printf("로그인 비번 : ");
			loginPw = sc.nextLine();
			System.out.printf("로그인 비번확인 : ");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
			break;
		}
		System.out.printf("이름 : ");
		String name = sc.nextLine();
		String gender;
		w1: while (true) {
			System.out.printf("남자 / 여자 : ");
			gender = sc.nextLine();
			if (!gender.equals("남자") && !gender.equals("여자")) {
				System.out.println("남자 또는 여자를 입력해주세요");
				continue w1;
			}
			break; // 남자, 여자 라고 잘입력해서 조건충족후 탈출
		}
		int height = 0;
		// 키 입력 받기
		while (true) {
			System.out.printf("키 : ");
			if (sc.hasNextInt()) {
				height = sc.nextInt();
				sc.nextLine(); // 버퍼 비우기
				break;
			} else {
				System.out.println("키를 숫자로 입력해주세요.");
				sc.nextLine(); // 잘못된 입력값 처리를 위해 버퍼 비우기
				continue;
			}
		}
		int weight = 0;
		// 몸무게 입력 받기
		while (true) {
			System.out.printf("몸무게 : ");
			if (sc.hasNextInt()) {
				weight = sc.nextInt();
				sc.nextLine(); // 버퍼 비우기
				break;
			} else {
				System.out.println("몸무게를 숫자로 입력해주세요.");
				sc.nextLine(); // 잘못된 입력값 처리를 위해 버퍼 비우기
				continue;
			}
		}

		int bmiId = MemberController.getBmi(height, weight);
		memberService.join(loginId, loginPw, name, gender, height, weight, bmiId);

		System.out.println("회원가입이 완료되었습니다.");
	}

	public static String doLogin() {
		System.out.println();
		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();

		// 입력받은 아이디에 해당하는 회원이 존재하는지
		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("해당 회원은 존재하지 않습니다.");
			return null;
		}
		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호가 맞지 않습니다.");
			return null;
		}
		session.setLoginedMember(member);
		Member loginedMember = session.getLoginedMember();
		System.out.printf("로그인 성공! [%s]님 환영합니다!\n", loginedMember.name);
		return loginedMember.loginId;
	}

	private void doLogout() {
		session.setLoginedMember(null);
		System.out.println("");
		System.out.println("로그아웃 되었습니다.");
	}

	// My Page
	private void showInfo() {
		System.out.println();
		System.out.println("===[내 정보]===");
		System.out.println("이름 : " + session.getLoginedMember().name);
		System.out.println("성별 : " + session.getLoginedMember().gender);
		System.out.println("키 : " + session.getLoginedMember().height);
		System.out.println("몸무게  :" + session.getLoginedMember().weight);
		System.out.println("BMI : " + session.getLoginedMember().bmiId);
		System.out.println("===============");

		System.out.println("");

		printMemberLike();

		System.out.println();
		
		System.out.println("[My Page]========================");
		System.out.println(" - 메인화면 : main");
		System.out.println(" - 회원 정보 수정 : info modify");
		System.out.println(" - 비밀번호 변경 : pw modify");
		System.out.println(" - 회원 탈퇴 : withdraw");
		System.out.println("=================================");
		System.out.printf(">>> ");

		while (true) {
			String input = sc.nextLine();
			switch (input) {
			case "main":
				return; // 메인화면
			case "info modify":
				doModify(); // 회원 정보 수정
				return;
			case "pw modify":
				dochangePw(); // 비밀번호 변경
				return;
			case "withdraw":
				doDelete(session); // 회원 탈퇴
				return;
			default:
				System.out.println("존재하지 않는 명령어 입니다. 다시 입력해주세요.");
				break;
			}
		}
	}

	// 비밀번호 변경
	private void dochangePw() {
		System.out.println();
		System.out.println("비밀번호를 변경합니다.");
		System.out.printf("현재 비밀번호 : ");
		String nowPw = sc.nextLine();
		Member member = memberService.getMemberByLoginPw(nowPw);

		if (member == null) {
			System.out.println("비밀번호가 맞지 않습니다.");
			dochangePw();
			return;
		}

		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {
			System.out.printf("변경할 비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("비밀번호 확인 : ");
			loginPwConfirm = sc.nextLine();

			if (!loginPw.equals(loginPwConfirm)) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
			memberService.changeNewPw(loginPw, member.loginId);
			System.out.println("비밀번호 변경 완료");
			break;
		}
	}

	public static void doDelete(Session session) {
		Member loginedMember = session.getLoginedMember();
		System.out.println();
		System.out.println("진짜 탈퇴하시겠습니까 ? Y/N");
		// 수정함 : next() -> nextLine() - 이유 : next()로 하면 삭제 후 App클래스에서 다시 입력하세요가 출력됨
		String an = sc.nextLine();
		if (an.equals("Y") || an.equals("y")) {
			memberService.delete(loginedMember);
			System.out.println("회원 탈퇴 완료");
			session.setLoginedMember(null);
		} else if (an.equalsIgnoreCase("N")) {
			System.out.println("취소되었습니다.");
		}
	}

	// 수정 필요
	// 추가 , 회원 정보 수정 함수
	public static void doModify() {
		if (!session.isLogined()) {
			System.out.println("로그인을 먼저 하십시오");
		}
		Member loginedMember = session.getLoginedMember();
		System.out.println();
		System.out.println("회원 정보를 수정합니다.");
		System.out.printf("이름 : ");
		String name = sc.nextLine();
		String gender;
		w1: while (true) {
			System.out.printf("남자 / 여자 : ");
			gender = sc.nextLine();
			if (!gender.equals("남자") && !gender.equals("여자")) {
				System.out.println("남자 또는 여자를 입력해주세요");
				continue w1;
			}
			break; // 남자, 여자 라고 잘입력해서 조건충족후 탈출
		}
		System.out.printf("키 : ");
		int height = sc.nextInt();
		sc.nextLine();
		System.out.printf("몸무게 : ");
		int weight = sc.nextInt();
		sc.nextLine();
		int bmiId; // 추가
		bmiId = getBmi(height, weight);
		loginedMember.name = name;
		loginedMember.gender = gender;
		loginedMember.height = height;
		loginedMember.weight = weight;
		loginedMember.bmiId = bmiId;
		memberService.modify(bmiId, name, gender, height, weight, loginedMember.loginId);
		System.out.println("회원 정보 수정 완료");
	}

	// bmi 공식
	public static int getBmi(int height, int weight) {
		double bmi;
		int bmiId;

		bmi = weight / (Math.pow(((float) height / 100), 2));
		if (bmi <= 18.5) {
			bmiId = 1;
		} else if (bmi <= 22.9) {
			bmiId = 2;
		} else if (bmi <= 24.9) {
			bmiId = 3;
		} else if (bmi > 24.9) {
			bmiId = 3;
		} else {
			return 0;
		}
		return bmiId;
	}
	
	public void printMemberLike() {
		List<String> memberLikeList = memberService.getMemberLog(session.getLoginedMember().name);
		System.out.println("==================================[내가 추천한 운동과 식단]==================================");
		System.out.println(" 번호  ||               이름                ||          링크         ");
		System.out.println("---------------------------------------------------------------------------------------------");
		for (int i = 2; i < memberLikeList.size() + 2; i += 2) {
			for (int j = 0; j < 2; j++) {
				int index = i + j - 2;
				if (j == 0) {
					System.out.print(" " + (i / 2) + ".    || ");

				}
				System.out.printf("%30s", memberLikeList.get(index) + " || ");
				if (j == 1) {
					System.out.println();
				}
			}
			System.out.println();
		}
		System.out.println("=============================================================================================");
	}
}
