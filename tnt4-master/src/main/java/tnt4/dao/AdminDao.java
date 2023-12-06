package tnt4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tnt4.container.Container;
import tnt4.db.DBConnection;
import tnt4.dto.Exercise;
import tnt4.dto.Food;
import tnt4.dto.Member;
import tnt4.dto.NoticeBoard;
import tnt4.dto.QnABoard;

public class AdminDao {
	private DBConnection dbConnection;
	public static Scanner sc;

	public AdminDao() {
		dbConnection = Container.getDBConnection();
	}

	// 관리자 - 운동 리스트 반환
	public List<String> getAdminExerciseList(String selectPlace, String selectExercise) {
		List<String> exerciseList = new ArrayList<>();

		try {
			String query = "SELECT `id`, `name` FROM `exercise` WHERE location = ? AND kind = ? ";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1, selectPlace);
			preparedStatement.setString(2, selectExercise);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String exerciseInfo = "No." + id + " / " + name;
				exerciseList.add(exerciseInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exerciseList;
	}

	// 관리자 - 식단 리스트 반환
	public List<Food> getAdminFoodList() {
		List<Food> foodList = new ArrayList<>();

		try {
			String query = "SELECT `id`, `name` FROM `food`";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Food announcement = new Food(id, name);
				foodList.add(announcement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foodList;
	}

	// 관리자 - 공지사항 리스트 반환
	public List<NoticeBoard> getAdminNoticeList() {
		List<NoticeBoard> noticeList = new ArrayList<>();

		try {
			String query = "SELECT `id`, `title` FROM `noticeBoard`";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("title");
				NoticeBoard announcement = new NoticeBoard(id, name);
				noticeList.add(announcement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noticeList;
	}

	// 관리자 - QnA 리스트 반환
	public List<QnABoard> getAdminQnAList() {
		List<QnABoard> noticeList = new ArrayList<>();

		try {
			String query = "SELECT `id`, `userQuestionName` FROM `qna`";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("userQuestionName");
				QnABoard announcement = new QnABoard(id, name);
				noticeList.add(announcement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noticeList;
	}

	// 관리자 - 멤버 리스트 반환
	public List<Member> getAdminMemberList() {
		List<Member> memberList = new ArrayList<>();

		try {
			String query = "SELECT `id`, `name` FROM `member`";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Member member = new Member(id, name);
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return memberList;
	}

	// 운동 리스트에 아이템 추가
	public void writeAdminExercise(String writePlace, String writeExercise, String writeName, String writeLink,
			int writeBmiId, int writeLike) {
		try {
			String query = "INSERT INTO `exercise` (`location`, `kind`, `name`, `link`, `bmiId`, `like`) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1, writePlace);
			preparedStatement.setString(2, writeExercise);
			preparedStatement.setString(3, writeName);
			preparedStatement.setString(4, writeLink);
			preparedStatement.setInt(5, writeBmiId);
			preparedStatement.setInt(6, writeLike);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 식단 리스트에 아이템 추가
	public void writeAdminFood(String writeFoodName, int writeFoodKal, int writeFoodPro, int writeFoodBmiId,
			int writeFoodLike) {
		try {
			String query = "INSERT INTO `food` (`name`, `kal`, `pro`, `bmiId`, `like`) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1, writeFoodName);
			preparedStatement.setInt(2, writeFoodKal);
			preparedStatement.setInt(3, writeFoodPro);
			preparedStatement.setInt(4, writeFoodBmiId);
			preparedStatement.setInt(5, writeFoodLike);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 공지사항 작성
	public void writeAdminNotice(String writeNoticeTitle, String writeNoticeDetail) {
		try {
			String query = "INSERT INTO `noticeBoard` (`title`, `detail`, `updateDate`) VALUES (?, ?, NOW())";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1, writeNoticeTitle);
			preparedStatement.setString(2, writeNoticeDetail);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// QnA 작성
	public void writeAdminQnA(String writeUserQuestionName, String writeUserQuestionText, String writeAdminAnswerName,
			String writeAdminAnswerText) {
		try {
			String query = "INSERT INTO `qna` (`userQuestionName`," + " `userQuestionText`, " + "`adminAnswerName`, "
					+ "`adminAnswerText`) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1, writeUserQuestionName);
			preparedStatement.setString(2, writeUserQuestionText);
			preparedStatement.setString(3, writeAdminAnswerName);
			preparedStatement.setString(4, writeAdminAnswerText);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 운동 수정
	public void modifyAdminExercise(int itemId, String modifyName, String modifyLocation, String modifyKind,
			String modifyLink, int modifyLike, int modifyBmiId) {
		try {
			String query = "UPDATE `exercise` SET `name` = ?, `location` = ?, `kind` = ?, `link` = ?, `like` = ?, `bmiId` = ? WHERE `id` = ?";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1, modifyName);
			preparedStatement.setString(2, modifyLocation);
			preparedStatement.setString(3, modifyKind);
			preparedStatement.setString(4, modifyLink);
			preparedStatement.setInt(5, modifyLike);
			preparedStatement.setInt(6, modifyBmiId);
			preparedStatement.setInt(7, itemId); // 아이템 ID는 맨 마지막에 설정

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 식단 수정
	public void modifyAdminFood(int itemId, String modifyFoodName, int modifyFoodKal, int modifyFoodPro,
			int modifyFoodLike, int modifyFoodBmiId) {
		try {
			String query = "UPDATE `food` SET `name` = ?, `kal` = ?, `pro` = ?, `like` = ?, `bmiId` = ? WHERE `id` = ?";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setString(1, modifyFoodName);
			preparedStatement.setInt(2, modifyFoodKal);
			preparedStatement.setInt(3, modifyFoodPro);
			preparedStatement.setInt(4, modifyFoodLike);
			preparedStatement.setInt(5, modifyFoodBmiId);
			preparedStatement.setInt(6, itemId);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 공지사항 수정
	public void modifyAdminNotice(int itemId, String modifyTitle, String modifyDetail) {
		try {
			Connection connection = dbConnection.getConnection();
			String query = "UPDATE `noticeBoard` SET `title` = ?, `detail` = ?, `updateDate` = NOW() WHERE `id` = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, modifyTitle);
			preparedStatement.setString(2, modifyDetail);
			preparedStatement.setInt(3, itemId);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void modifyAdminQnA(int itemId, String modifyAdminAnswerName, String modifyAdminAnswerText) {
		try {
			Connection connection = dbConnection.getConnection();
			String query = "UPDATE `qna` SET `adminAnswerName` = ?, `adminAnswerText` = ? WHERE `id` = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, modifyAdminAnswerName);
			preparedStatement.setString(2, modifyAdminAnswerText);
			preparedStatement.setInt(3, itemId);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 선택한 리스트의 선택한 id(아이템) 삭제
	public boolean deleteAdminSelectItem(String selectList, int itemId) {
		try {
			String tableName;
			switch (selectList) {
			case "exercise":
				tableName = "exercise";
				break;
			case "food":
				tableName = "food";
				break;
			case "notice":
				tableName = "noticeBoard";
				break;
			case "QnA":
				tableName = "qna";
				break;
			case "member":
				tableName = "member";
				break;
			default:
				return false;
			}

			String query = "DELETE FROM `" + tableName + "` WHERE `id` = ?";
			PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, itemId);

			int affectedRows = preparedStatement.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Exercise getExercise(int itemId) {
		Exercise exercise = null;

		try {
			Connection connection = dbConnection.getConnection();
			String query = "SELECT * FROM `exercise` WHERE `id` = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemId);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String name = resultSet.getString("name");
				String location = resultSet.getString("location");
				String kind = resultSet.getString("kind");
				String link = resultSet.getString("link");
				int like = resultSet.getInt("like");
				int bmiId = resultSet.getInt("bmiId");

				exercise = new Exercise(itemId, name, location, kind, link, like, bmiId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exercise;
	}

	public Food getFood(int itemId) {
		Food food = null;
		try {
			Connection connection = dbConnection.getConnection();
			String query = "SELECT * FROM `food` WHERE `id` = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemId);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String name = resultSet.getString("name");
				int kal = resultSet.getInt("kal");
				int pro = resultSet.getInt("pro");
				int like = resultSet.getInt("like");
				int bmiId = resultSet.getInt("bmiId");

				food = new Food(itemId, name, kal, pro, like, bmiId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return food;
	}

	public NoticeBoard getNotice(int itemId) {
		NoticeBoard noticeBoard = null;
		try {
			Connection connection = dbConnection.getConnection();
			String query = "SELECT * FROM `noticeBoard` WHERE `id` = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemId);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String title = resultSet.getString("title");
				String detail = resultSet.getString("detail");

				noticeBoard = new NoticeBoard(itemId, title, detail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noticeBoard;
	}

	public QnABoard getQnA(int itemId) {
		QnABoard qnaBoard = null;
		try {
			Connection connection = dbConnection.getConnection();
			String query = "SELECT * FROM `qna` WHERE `id` = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemId);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String userQuestionName = resultSet.getString("userQuestionName");
				String userQuestionText = resultSet.getString("userQuestionText");
				String adminAnswerName = resultSet.getString("adminAnswerName");
				String adminAnswerText = resultSet.getString("adminAnswerText");

				qnaBoard = new QnABoard(itemId, userQuestionName, userQuestionText, adminAnswerName, adminAnswerText);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qnaBoard;
	}
}