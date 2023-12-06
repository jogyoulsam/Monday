package tnt4.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tnt4.container.Container;
import tnt4.db.DBConnection;
import tnt4.dto.Exercise;
import tnt4.dto.Food;
import tnt4.dto.Member;
import tnt4.dto.NoticeBoard;
import tnt4.dto.QnABoard;
import tnt4.service.MemberService;

public class OperationDao extends Dao {
	private DBConnection dbConnection;
	private Exercise exercise;
	private Food food;

	private MemberService memberService;
	private Member member;

	public OperationDao() {
		dbConnection = Container.getDBConnection();
		memberService = Container.memberService;
	}

	// 운동 리스트 가져옴 (selectPlace : 헴스장/홈트, selectExercise : 유산소/무산소)
	public List<String> getExerciseList(String selectPlace, String selectExercise, String loginId) {
		List<String> exerciseList = new ArrayList<>();

		memberService = Container.memberService;
		member = memberService.getMemberByLoginId(loginId);

		// bmi id가 1, 3 인 사람은 bmi id 가 2인 운동도 추천 받게 하려고 아래 쿼리문 수정
		try {
			String query = "SELECT `name`,`id`,`like`,`link` FROM `exercise` WHERE location = ? AND kind = ? AND (bmiId = ? OR bmiId =2)";
			// Statement 클래스보다 기능이 향상된 클래스
			// 코드의 안정성과 가독성이 높다.
			// 인자 값으로 전달이 가능하다.
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1, selectPlace);
			preparedStatement.setString(2, selectExercise);
			preparedStatement.setLong(3, member.bmiId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String like = resultSet.getString("like");
				String link = resultSet.getString("link");
				exerciseList.add(id);
				exerciseList.add(name);
				exerciseList.add(like);
				exerciseList.add(link);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exerciseList;
	}

	public List<String> getExerciseList2(String selectPlace, String selectExercise, String loginId) {
		List<String> exerciseList = new ArrayList<>();

		memberService = Container.memberService;
		member = memberService.getMemberByLoginId(loginId);

		try {
			String query = "SELECT `name`,`id`,`like`,`link` FROM `exercise` WHERE location = ? AND kind = ? ORDER BY `like` DESC";
			// Statement 클래스보다 기능이 향상된 클래스
			// 코드의 안정성과 가독성이 높다.
			// 인자 값으로 전달이 가능하다.
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1, selectPlace);
			preparedStatement.setString(2, selectExercise);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String like = resultSet.getString("like");
				String link = resultSet.getString("link");
				exerciseList.add(id);
				exerciseList.add(name);
				exerciseList.add(like);
				exerciseList.add(link);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exerciseList;
	}

	// 전체 운동 불러오기 맨앞에 로그인후 랜덤 운동 뛰울떄 피욜함
	public List<String> getExerciseList3() {
		List<String> exerciseList = new ArrayList<>();

		try {// bmi id가 1, 3 인 사람은 bmi id 가 2인 운동도 추천 받게 하려고 아래 쿼리문 수정
			String query = "SELECT `name`,`id`,`link` FROM `exercise`";
			// Statement 클래스보다 기능이 향상된 클래스
			// 코드의 안정성과 가독성이 높다.
			// 인자 값으로 전달이 가능하다.
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");

				String link = resultSet.getString("link");
				exerciseList.add(id);
				exerciseList.add(name);

				exerciseList.add(link);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exerciseList;
	}

	// 식단 리스트
	public List<String> getFoodList(int num, String loginId) {
		List<String> foodList = new ArrayList<>();

		if (num == 1) { // num 1 은 다이어트 식단
			num = 3; // bmiId 가 3인 사람 (과체중)
		} else if (num == 2) {// num 2는 벌크업 식단
			num = 1; // bmiId 가 1인 사람 (저체중)
		}
		try {
			String query = "SELECT `id`,`name`,`like`,`kal`,`pro` FROM `food` WHERE `bmiId` = ? or `bmiId` = 2 ORDER BY `like` DESC";

			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setLong(1, num);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String like = resultSet.getString("like");
				String kal = resultSet.getString("kal");
				String pro = resultSet.getString("pro");
				foodList.add(id);
				foodList.add(name);
				foodList.add(like);
				foodList.add(kal);
				foodList.add(pro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foodList;
	}

	// 공지사항
	public List<NoticeBoard> getNoticeBoard() {
		List<NoticeBoard> noticeBoardList = new ArrayList<>();

		try {
			String query = "SELECT `id`, `title`, `detail` FROM `noticeBoard`";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("title");
				String detail = resultSet.getString("detail");
				NoticeBoard announcement = new NoticeBoard(id, name, detail);
				noticeBoardList.add(announcement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noticeBoardList;
	}

	public List<QnABoard> getQnABoard() {
		List<QnABoard> QnABoardList = new ArrayList<>();

		try {
			String query = "SELECT `id`, `userQuestionName`, `userQuestionText`, `adminAnswerName`, `adminAnswerText` FROM `QnA`";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String userQuestionName = resultSet.getString("userQuestionName");
				String userQuestionText = resultSet.getString("userQuestionText");
				String adminAnswerName = resultSet.getString("adminAnswerName");
				String adminAnswerText = resultSet.getString("adminAnswerText");
				QnABoard announcement = new QnABoard(id, userQuestionName, userQuestionText, adminAnswerName,
						adminAnswerText);
				QnABoardList.add(announcement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return QnABoardList;
	}

	public void upLikeExercise(int id, String loginId, String what) {
		exercise = getExerciseById(id); // 운동의 추천수가 필요해서 불러와야함
		member = memberService.getMemberByLoginId(loginId);// 반복추천 막기위해
		int num = getlogByName(member.name, exercise.name, what);
		if (num == 1) {// 추천을 할떄에는 추천 로그 db는 새로 추가되야함, num이 1이라는 것은 추천 로그에 없다는걸 의미, 추천한적이없다
			setAddlog(member.name, exercise.name, what, exercise.link);
		} else if (num == -1) { // 추천이 취소될떄 추천 로그 db 에는 삭제되야함
			setDeletelog(member.name, exercise.name, what);
		}

		StringBuilder sb = new StringBuilder();

		sb.append(String.format("UPDATE `exercise` "));
		sb.append(String.format("SET `like` = '%d' ", exercise.like + num));// // num 이 1이면 추천 더하기 ,num이 -1 추천 취소
		sb.append(String.format("WHERE `id` = '%d' ", id));

		dbConnection.insert(sb.toString());
	}

	// Exercise 를 갖고 오는 함수 추가
	public Exercise getExerciseById(int id) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `exercise` "));
		sb.append(String.format("WHERE id = '%d' ", id));
		Map<String, Object> row = dbConnection.selectRow(sb.toString());
		if (row.isEmpty()) {
			return null;
		}
		return new Exercise(row);
	}

	public void upLikeFood(int id, String loginId, String what) {
		food = getFoodById(id);

		memberService = Container.memberService;
		member = memberService.getMemberByLoginId(loginId); // 반복추천 막기위해

		int num = getlogByName(member.name, food.name, what); // 이부분도 위에 운동 추천 함수랑 인자 이름만 바꾸고 동일
		if (num == 1) { // 추천을 할떄에는 추천 로그 db는 새로 추가되야함
			setAddlog(member.name, food.name, what, null);
		} else if (num == -1) { // 추천이 취소될떄 추천 로그 db 에는 삭제되야함
			setDeletelog(member.name, food.name, what);
		}

		StringBuilder sb = new StringBuilder();

		sb.append(String.format("UPDATE `food` "));
		sb.append(String.format("SET `like` = '%d' ", food.like + num)); // num 이 1이면 추천 더하기 ,num이 -1 추천 취소
		sb.append(String.format("WHERE `id` = '%d' ", id));

		dbConnection.insert(sb.toString());
	}

	public Food getFoodById(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `food` "));
		sb.append(String.format("WHERE id = '%d' ", id));

		Map<String, Object> row = dbConnection.selectRow(sb.toString());

		if (row.isEmpty()) {
			return null;
		}
		return new Food(row);
	}

	// 함수 이름이 애매한데 회원이름과 추천한 항목을 받아서 그 회원이 그 항목을 추천한지 없는지 판별해주는 함수
	// 위에 추천 해주는 함수에서 추천을 더할껀지 뺼껀지 구별하기 위해서 int 로 리턴값을받음
	public int getlogByName(String memberName, String likeName, String what) { //
		List<String> logList = new ArrayList<>();

		try {
			String query = "SELECT `userName` FROM `likeLog` WHERE userName = ? AND likeName = ? ";

			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1, memberName);
			preparedStatement.setString(2, likeName);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				String userName = resultSet.getString("userName");

				logList.add(userName);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (logList.size() > 0) {
			return -1;
		} else if (logList.size() == 0) {
			System.out.println("");
			return 1;
		}

		return 20; // 오류 방지

	}

	// 추천 기록이 없다면 추천 기록에 추가해주는 함수
	public void setAddlog(String memberName, String likeName, String what, String exerciseLink) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("INSERT INTO `likeLog` "));
		sb.append(String.format("(userName, likeName, likeWhat,link) "));
		sb.append(String.format("VALUES ('%s','%s','%s','%s') ", memberName, likeName, what, exerciseLink));

		dbConnection.insert(sb.toString());
		System.out.println("추천 되었습니다.");
	}

	//// 추천 기록이 이미 있다면 추천 기록에서 제거해주는 함수
	public void setDeletelog(String memberName, String likeName, String what) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("DELETE FROM `likeLog` "));
		sb.append(String.format("WHERE userName='%s' AND likeName ='%s'AND likeWhat = '%s' ", memberName, likeName,
				what));
		dbConnection.insert(sb.toString());
		System.out.println("이미 추천한 기록이 있어서 추천이 취소되었습니다.");
	}

	public void userWriteQnA(String userWriteQnAName, String userWriteQnAText) {
		try {
			String query = "INSERT INTO `qna` (`userQuestionName`, `userQuestionText`) VALUES (?, ?)";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1, userWriteQnAName);
			preparedStatement.setString(2, userWriteQnAText);

			preparedStatement.executeUpdate();

			System.out.println("유저의 QnA가 성공적으로 등록되었습니다.");
			System.out.println("[입력한 값]");
			System.out.println(" - 제목 : " + userWriteQnAName);
			System.out.println(" - 내용 : " + userWriteQnAText);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
