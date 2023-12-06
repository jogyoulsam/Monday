package tnt4.controller;

import java.util.List;
import java.util.Scanner;

import tnt4.container.Container;
import tnt4.dto.NoticeBoard;
import tnt4.dto.QnABoard;
import tnt4.service.OperationService;

public class OperationController extends Controller {
	private Scanner sc;
	private OperationService operationService;

	// 생성자 - 스캐너, 세션(현재 유저 정보), 서비스 사용 가능하게 함
	public OperationController() {
		sc = Container.getScanner();
		operationService = Container.operationService;
	}

	public void doAction(String command, String loginId) {
		switch (command) {
		case "select item":
			showSelect(loginId);
			break;
		case "notice board":
			showAnnouncement();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다. (operation)");
			break;
		}
	}

	private void showAnnouncement() {
		while (true) {
			System.out.println();
			System.out.println("========[공지사항]========");
			System.out.println("번호  || 제목");
			System.out.println("--------------------------");
			List<NoticeBoard> noticeBoardList = operationService.getNoticeBoard();

			int index = 1;
			for (NoticeBoard noticeBoard : noticeBoardList) {
				System.out.println("No. " + index + " || " + noticeBoard.getTitle());
				index++;
			}
			System.out.println("==========================");

			// 가독성 때문에 한 줄 띄움
			System.out.println("");

			System.out.println("============[QnA]==============");
			System.out.println("번호  || 제목");
			System.out.println("--------------------------");
			List<QnABoard> QnABoardList = operationService.getQnABoard();

			index = 1;
			for (QnABoard QnABoard : QnABoardList) {
				System.out.println("No. " + index + " || " + QnABoard.getUserQuestionName());
				index++;
			}
			System.out.println("===============================");

			System.out.println();

			System.out.println("[공지사항 및 QnA]===========");
			System.out.println(" - 공지사항 보기 : board ID");
			System.out.println(" - QnA 보기 : QnA ID");
			System.out.println(" - QnA 작성 : QnA write");
			System.out.println(" - 메인으로 돌아가기 : main");
			System.out.println("============================");
			System.out.printf(">>> ");
			String userInput = sc.nextLine();

			System.out.println();
			switch (userInput) {
			case "main":
				System.out.println("메인 화면으로 돌아갑니다.");
				return;
			case "QnA write":
				userWriteQnA();
				break;
			default:
				if (userInput.startsWith("board") && userInput.length() > 6) {
					// "board" 다음에 숫자가 오는지 확인
					String selectedIdStr = userInput.substring(6).trim();
					// 숫자 부분이 비어있는지 확인
					if (selectedIdStr.isEmpty()) {
						System.out.println("잘못된 입력입니다. 숫자로 다시 입력하세요.");
						continue; // 루프의 처음으로 돌아가서 유효한 입력을 받도록 함
					}
					try {
						int selectedId = Integer.parseInt(selectedIdStr);
						selectedId--;
						if (selectedId >= 0 && selectedId < noticeBoardList.size()) {
							NoticeBoard selectedNotice = noticeBoardList.get(selectedId);
							System.out.printf("==============================[%s]==============================\n",
									selectedNotice.getTitle());
							System.out.println("내용 : " + selectedNotice.getDetail());
							System.out.println(
									"============================================================================");
						} else {
							System.out.println("해당하는 공지사항이 없습니다.");
						}
					} catch (NumberFormatException e) {
						System.out.println("잘못된 입력입니다. 숫자로 다시 입력하세요.");
					}
				} else if (userInput.startsWith("QnA") && userInput.length() > 3) {
					// "QnA" 다음에 숫자가 오는지 확인
					String selectedIdStr = userInput.substring(3).trim();
					// 숫자 부분이 비어있는지 확인
					if (selectedIdStr.isEmpty()) {
						System.out.println("잘못된 입력입니다. 숫자로 다시 입력하세요.");
						continue; // 루프의 처음으로 돌아가서 유효한 입력을 받도록 함
					}
					try {
						int selectedId = Integer.parseInt(selectedIdStr);
						selectedId--;
						if (selectedId >= 0 && selectedId < QnABoardList.size()) {
							QnABoard selectedQnA = QnABoardList.get(selectedId);
							System.out.printf(
									"==================================[%s]==================================\n",
									userInput);
							System.out.println("질문 : " + selectedQnA.getUserQuestionName());
							System.out.println("질문내용 : " + selectedQnA.getUserQuestionText());
							System.out.println(
									"---------------------------------------------------------------------------");
							System.out.println("답변 : " + selectedQnA.getAdminAnswerName());
							System.out.println("답변내용 : " + selectedQnA.getAdminAnswerText());
							System.out.println(
									"===========================================================================");
						} else {
							System.out.println("해당하는 QnA가 없습니다.");
						}
					} catch (NumberFormatException e) {
						System.out.println("잘못된 입력입니다. 숫자로 다시 입력하세요.");
					}
				} else {
					System.out.println("잘못된 입력입니다. 다시 입력하세요.");
				}
			}
		}
	}

	// QnA 작성 기능
	private void userWriteQnA() {
		System.out.println("QnA 질문을 작성하세요.");
		System.out.println("===========================================================================");
		System.out.printf("제목 : ");
		String userWriteQnAName = sc.nextLine();
		System.out.printf("내용 : ");
		String userWriteQnAText = sc.nextLine();
		System.out.println("===========================================================================");

		operationService.userWriteQnA(userWriteQnAName, userWriteQnAText);
	}

	// 운동/ 식단 선택
	private void showSelect(String loginId) {
		System.out.println("");
		System.out.println("운동과 식단 중 선택하세요.");
		System.out.println("운동 / 식단");
		System.out.println("메인 : main");
		System.out.printf(">>> ");
		String select = sc.nextLine();

		switch (select) {
		case "운동":
			showSelectPlace(loginId);
			break;
		case "식단":
			showSelectEat(loginId);
			break;
		case "main":
			System.out.println("메인 화면으로 돌아갑니다.");
			break;
		// 그 외의 입력시 다시 실행
		default:
			System.out.println("올바른 값을 입력하세요.");
			showSelect(loginId); // 재귀 호출하여 메서드를 다시 실행
			break;
		}
	}

	// 헬스장/홈트 선택
	private void showSelectPlace(String loginId) {
		System.out.println("");
		System.out.println("어디에서 운동 하십니까?");
		System.out.println("헬스장 / 홈트");
		System.out.println("메인 : main");
		System.out.printf(">>> ");

		String selectPlace = sc.nextLine();

		// 헬스장 or 홈트 아니면 다시 실행
		switch (selectPlace) {
		case "헬스장":
		case "홈트":
			showSelectExercise(selectPlace, loginId);
			return;
		case "main":
			System.out.println("메인 화면으로 돌아갑니다.");
			return;
		default:
			System.out.println("올바른 장소를 입력하세요.");
			break;
		}
	}

	// 유산소/무산소 선택
	private void showSelectExercise(String selectPlace, String loginId) {
		System.out.println();
		System.out.println("어떤 운동을 하시겠습니까?");
		System.out.println("유산소 / 무산소");
		System.out.println("메인 : main");
		System.out.printf(">>> ");

		String selectExercise = sc.nextLine();

		// 유산소 or 무산소 아니면 다시 실행
		// 입력한 값을 매개변수로 전달
		switch (selectExercise) {
		case "유산소":
		case "무산소":
			showPlaceExercise(selectPlace, selectExercise, loginId);
			userUpLike(selectPlace, selectExercise, loginId);
			return;
		case "main":
			System.out.println("메인 화면으로 돌아갑니다.");
			return;
		default:
			System.out.println("올바른 운동 종류를 입력하세요.");
			showSelectExercise(selectPlace, loginId);
			break;
		}
	}

	private void userUpLike(String selectPlace, String selectExercise, String loginId) {
		while (true) {
			System.out.println("추천 하고 싶은 운동이 있다면 그 운동의 번호를 입력해주세요.");
			System.out.println("메인페이지로 돌아가려면 main을 입력해주세요");
			System.out.printf(">>> ");
			String input = sc.nextLine();

			if (input.equals("main")) {
				return; // 메인 페이지로 돌아가기
			} else {
				try {
					int id = Integer.parseInt(input);
					operationService.likeExercise(id, loginId, "exercise"); // 중복 추천 막기 위해 아이디랑 운동 인자로 넣기
					showPlaceExercise(selectPlace, selectExercise, loginId);
				} catch (NumberFormatException e) {
					System.out.println("올바른 운동 번호를 입력하세요.");
				}
			}
		}
	}

	// 입력한 헬스장/홈, 유산소/무산소에 따라 DB에서 Data 가져옴
	private void showPlaceExercise(String selectPlace, String selectExercise, String loginId) {
		List<String> exerciseList1 = operationService.getExerciseList(selectPlace, selectExercise, loginId);
		List<String> exerciseList2 = operationService.getExerciseList2(selectPlace, selectExercise, loginId);
		System.out.println();
		System.out.println(
				"=========================================[회원들이 추천한 운동 TOP 3]===============================================");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------");
		System.out.println("No. ||               이름                      ||   추천수  ||             유튜브 링크            ");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------");
		printExerciseList(exerciseList2, 12);

		System.out.println();

		System.out.println(
				"==============================================[회원님의 맞춤 운동]==================================================");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------");
		System.out.println("No. ||               이름                      ||   추천수  ||             유튜브 링크            ");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------");
		printExerciseList(exerciseList1, exerciseList1.size());
		System.out.println(
				"====================================================================================================================");
	}

	public void printExerciseListByRandom(List<String> exerciseList, int num1, int num2) {

		int[] box = random(exerciseList.size(), num2, num1);

		for (int i = 0; i < num1; i += 1) { // 3개까지만 나오게하려고
			for (int j = 0; j < num2; j++) {
				int index = box[i] + j;
				if (index < exerciseList.size()) {
					System.out.printf("%2s   ||", (String) exerciseList.get(index));
					System.out.print("       ");
				}
			}
			System.out.println();
		}
	}

	// 운동 리스트 출력
	public void printExerciseList(List<String> exerciseList, int num) {

		for (int i = 0; i < num; i += 4) { // 3개까지만 나오게하려고 12를 적음
			for (int j = 0; j < 4; j++) {
				int index = i + j;
				System.out.printf("%2s  ||", (String) exerciseList.get(index));
				System.out.print("       ");
			}
			System.out.println();
		}
	}

	// 식단 선택
	// 다이어트 식단 - DB 식단 2, 3번, 벌크업 식단 - DB 1, 2번
	private void showSelectEat(String loginId) {
		String select = "";
		while (true) {
			System.out.println();
			System.out.println("어떤 식단을 선택하시겠습니까?");
			System.out.println("다이어트 / 벌크업");
			System.out.println("메인 : main");
			System.out.printf(">>> ");
			select = sc.nextLine();
			if (!select.equals("다이어트") && !select.equals("벌크업") && !select.equals("main")) {
				System.out.println("올바른 명령어를 입력해주세요");
				continue;
			}
			System.out.println();
			break;
		}
		w1: switch (select) {
		case "main":
			System.out.println("메인 화면으로 돌아갑니다.");
			break;
		case "다이어트":
			// 인자값 쓴 이유 : 각 식단 리스트 불러오는 함수가 같아서 1/2로 구분 함
			showDiet(1, loginId);
			System.out.println();
			while (true) {
				System.out.println("추천하고 싶은 음식이 있다면 번호를 입력해주세요");
				System.out.println("메인페이지로 돌아가려면 main을 입력해주세요");
				System.out.printf(">>> ");
				String input = sc.nextLine();

				if (input.equals("main")) {
					select = "main";
					break w1;// 메인 페이지로 돌아가기
				} else {
					try {
						int id = Integer.parseInt(input);
						operationService.likeFood(id, loginId, "food"); // 중복 추천 막기 위해 아이디랑 운동 인자로 넣기
						showDiet(1, loginId);
					} catch (NumberFormatException e) {
						System.out.println("올바른 식단 번호를 입력하세요.");
					}
				}
				showDiet(1, loginId);
				continue;
			}
		case "벌크업":
			// 인자값 쓴 이유 : 각 식단 리스트 불러오는 함수가 같아서 1/2로 구분 함
			showBulk(2, loginId);
			System.out.println();
			while (true) {
				System.out.println("추천하고 싶은 음식이 있다면 번호를 입력해주세요");
				System.out.println("메인페이지로 돌아가려면 main을 입력해주세요");
				System.out.printf(">>> ");
				String input = sc.nextLine();
				if (input.equals("main")) {
					select = "main";
					break w1;// 메인 페이지로 돌아가기
				} else {
					try {
						int id = Integer.parseInt(input);
						operationService.likeFood(id, loginId, "food"); // 중복 추천 막기 위해 아이디랑 운동 인자로 넣기
						showDiet(1, loginId);
					} catch (NumberFormatException e) {
						System.out.println("올바른 식단 번호를 입력하세요.");
					}
				}
				showBulk(2, loginId);
				continue;
			}
		}
	}

	// 다이어트 식단 가져옴
	private void showDiet(int num, String loginId) {
		List<String> foodList = operationService.getFoodList(num, loginId);

		int[] box = random(foodList.size(), 5, 5);
		System.out.println("");
		System.out.println("================[회원님 맞춤 다이어트 식단]==========================");
		System.out.println("No. ||     이름       ||  추천수   || 100g당 칼로리 / 단백질 g ");
		System.out.println("---------------------------------------------------------------------");
		for (int i = 0; i < 5; i += 1) { // 5개까지만 나오게하려고
			for (int j = 0; j < 5; j++) {
				int index = box[i] + j;
				if (index < foodList.size()) {
					System.out.printf("%3s ||", (String) foodList.get(index));
					System.out.print("       ");
				}
			}
			System.out.println();
		}
		System.out.println("=====================================================================");

		System.out.println("");

		System.out.println(" ");
		System.out.println("===========================[전체 다이어트 식단]======================");
		System.out.println("No. ||     이름       ||  추천수   || 100g당 칼로리 / 단백질 g ");
		System.out.println("---------------------------------------------------------------------");
		for (int i = 0; i < foodList.size(); i += 5) { // 3개까지만 나오게하려고 12를 적음
			for (int j = 0; j < 5; j++) {
				int index = i + j;
				if (index < foodList.size()) {
					System.out.printf("%3s ||", (String) foodList.get(index));
					System.out.print("       ");
				}
			}
			System.out.println();
		}
		System.out.println("=====================================================================");
	}

	// 벌크업 식단 가져옴
	private void showBulk(int num, String loginId) {
		List<String> foodList = operationService.getFoodList(num, loginId);
		int[] box = random(foodList.size(), 5, 5);

		System.out.println(" ");
		System.out.println("=======================[회원님 맞춤 벌크업 식단]=====================");
		System.out.println("No. ||       이름      ||   추천수   || 100g당 칼로리 / 단백질 g ");
		System.out.println("---------------------------------------------------------------------");
		for (int i = 0; i < 5; i += 1) { // 5개까지만 나오게하려고
			for (int j = 0; j < 5; j++) {
				int index = box[i] + j;
				if (true) {
					System.out.printf("%3s ||", (String) foodList.get(index));
					System.out.print("        ");
				}
			}
			System.out.println();
		}
		System.out.println("=====================================================================");

		System.out.println("");

		System.out.println("");
		System.out.println("=========================[전체 벌크업 식단] =========================");
		System.out.println("No. ||       이름      ||  추천수    || 100g당 칼로리 / 단백질 g ");
		System.out.println("---------------------------------------------------------------------");

		for (int i = 0; i < foodList.size(); i += 5) { // 3개까지만 나오게하려고 12를 적음
			for (int j = 0; j < 5; j++) {
				int index = i + j;
				if (index < foodList.size()) {

					System.out.printf("%3s ||", (String) foodList.get(index));
					System.out.print("        ");
				}
			}
			System.out.println();
		}
		System.out.println("=====================================================================");
	}

	private int[] random(int listSize, int collumNum, int printNum) {
		int Lotto[] = new int[printNum];// 로또 번호 받을 배열, 몇개 출력할것인지
		int Random = 0;// Random숫자 담을 변수

		/***** 최종 완성된 로또 번호 입력 반복문 *****/
		for (int i = 0; i < printNum; i++) {

			/***** 로또 번호 생성 반복문 *****/
			w1: while (true) {
				Random = (int) (Math.random() * listSize); // 하단 추가설명 1번

				if (Random == 0 || Random % collumNum != 0) { // 운동은 4개의 항목 출력
					// 식단은 5개의 항목출력
					continue;// 영이 나올 경우
				}
				for (int j = 0; j < printNum; j++) {
					if (Random == Lotto[j]) {

						continue w1;
					} // 같은 숫자일 경우
				}
				/**** 모든 조건 만족했다면 ****/
				break;
			}
			// ****모든 검사를 마친 로또번호에 집어 넣자****//
			Lotto[i] = Random;
		}
		return Lotto;
	}
}