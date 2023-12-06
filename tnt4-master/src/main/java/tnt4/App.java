package tnt4;

import java.util.List;

import tnt4.container.Container;
import tnt4.controller.AdminController;
import tnt4.controller.Controller;
import tnt4.controller.MemberController;
import tnt4.controller.OperationController;
import tnt4.controller.Session;
import tnt4.db.DBConnection;
import tnt4.service.OperationService;

public class App {
	private Session session;
	
	String loginId;
	
	OperationService operationService;
	
	public App() {
		DBConnection.DB_NAME = "sbs_proj";
		DBConnection.DB_USER = "sbsst";
		DBConnection.DB_PASSWORD = "sbs123414";
		DBConnection.DB_PORT = 3306;

		session = Container.getSession();

		Container.getDBConnection().connect();
		
		operationService = Container.operationService;
	}

	public void start() {
		System.out.println("==프로그램 시작==");

		OperationController operationController = new OperationController();
		MemberController memberController = new MemberController();
		AdminController adminController = new AdminController();

		while (true) {
			String select = null;
			if (!session.isLogined()) {
				System.out.println();
				System.out.println("[월요일]");
				System.out.println("- 부터 요요 없는 일요일 까지!");
				System.out.println();
				System.out.println("=======명령어 모음=======");
				System.out.println("로그인 : member login");
				System.out.println("회원가입 : member join");
				System.out.println("프로그램 종료: system exit");
				System.out.println("=========================");
				System.out.printf(">>> ");
				select = Container.getScanner().nextLine();

				switch (select) {
				case "member login":
					loginId = MemberController.doLogin();
					break;
				case "member join":
					MemberController.doJoin();
					break;
				case "system exit":
					System.out.println("==프로그램을 종료합니다==");
					return; // 프로그램 종료
				default:
					System.out.println("다시 입력하세요");
				}
				continue;
			}
			System.out.println("");
			List<String> exerciseList1 = operationService.getExerciseList();
			System.out.println("===========================================[랜덤 운동 Tip]================================================");
			System.out.println("번호 ||                 이름                     ||                 운동 영상 링크                      ||");
			System.out.println("----------------------------------------------------------------------------------------------------------");
			operationController.printExerciseListByRandom(exerciseList1,1,3);
			System.out.println("==========================================================================================================");
			System.out.println("");
			System.out.println("[Main Page]======================");
			System.out.println(" - 운동 / 식단 선택 : select item");
			System.out.println(" - 공지사항 / QnA : notice board");
			System.out.println(" - My Page : member info");
			System.out.println(" - 로그아웃 : member logout");
			System.out.println(" - 프로그램 종료 : system exit");
			System.out.println("=================================");

			if (session.getLoginedMember().loginId.equals("admin")) {
				System.out.println("");
				System.out.println("[관리자 권한 명령어 목록]========");
				System.out.println(" - 운동 관리 : exercise");
				System.out.println(" - 식단 관리 : food");
				System.out.println(" - 공지사항 관리 : notice");
				System.out.println(" - QnA 관리 : QnA");
				System.out.println(" - 유저 관리 : member");
				System.out.println("=================================");
			}

			System.out.printf("명령어 >>> ");
			String command = Container.getScanner().nextLine();
			command = command.trim();

			// 아무것도 입력하지 않으면 다시 실행
			if (command.length() == 0) {
				continue;
			}

			// 시스템 종료
			if (command.equals("system exit")) {
				Container.getDBConnection().close(); // DB 연결 닫기
				break;
			}

			Controller controller = null;

			// 관리자 권한 확인
			if (session.getLoginedMember().loginId.equals("admin")) {
				// 관리자 모드로 진입
				switch (command) {
				case "exercise":
				case "food":
				case "notice":
				case "QnA":
				case "member":
					controller = adminController;
					break;
				case "member logout":
				case "member info":
					controller = memberController;
					break;
				case "select item":
				case "notice board":
					controller = operationController;
					break;
				default:
					System.out.println("존재하지 않는 명령어입니다.");
					continue;
				}
			} else if (!session.getLoginedMember().loginId.equals("admin")) {
				// 일반 사용자 모드로 진입
				switch (command) {
				case "member logout":
				case "member info":
					controller = memberController;
					break;
				case "select item":
				case "notice board":
					controller = operationController;
					break;
				default:
					System.out.println("존재하지 않는 명령어입니다.");
					continue;
				}
			}
			// 선택한 컨트롤러의 doAction 메서드 호출
			controller.doAction(command, loginId);
		}
		Container.getScanner().close();
		System.out.println();
		System.out.println("==프로그램을 종료합니다==");
	}
}
