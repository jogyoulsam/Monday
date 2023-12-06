package tnt4.service;

import java.util.List;

import tnt4.container.Container;
import tnt4.dao.AdminDao;
import tnt4.dto.Exercise;
import tnt4.dto.Food;
import tnt4.dto.Member;
import tnt4.dto.NoticeBoard;
import tnt4.dto.QnABoard;

public class AdminService {
	private AdminDao adminDao;

	public AdminService() {
		adminDao = Container.adminDao;
	}

	// 관리자 - 운동 리스트 반환
	public List<String> getAdminExerciseList(String selectPlace, String selectExercise) {
		return adminDao.getAdminExerciseList(selectPlace, selectExercise);
	}

	// 관리자 - 식단 리스트 반환
	public List<Food> getAdminFoodList() {
		return adminDao.getAdminFoodList();
	}

	// 관리자 - 공지사항 리스트 반환
	public List<NoticeBoard> getAdminNoticeList() {
		return adminDao.getAdminNoticeList();
	}

	// 관리자 - QnA 리스트 반환
	public List<QnABoard> getAdminQnAList() {
		return adminDao.getAdminQnAList();
	}

	// 관리자 - 멤버 리스트 반환
	public List<Member> getAdminMemberList() {
		return adminDao.getAdminMemberList();
	}
	
	// 운동 쓰기
	public void writeAdminExercise(String writePlace, String writeExercise, String writeName, String writeLink, int writeBmiId) {
		adminDao.writeAdminExercise(writePlace, writeExercise, writeName, writeLink, writeBmiId, 0);
	}
	
	// 식단 쓰기
	public void writeAdminFood(String writeFoodName, int writeFoodKal, int writeFoodPro, int writeFoodBmiId) {
		adminDao.writeAdminFood(writeFoodName, writeFoodKal, writeFoodPro, writeFoodBmiId, 0);
	}
	
	// 공지사항 쓰기
	public void writeAdminNotice(String writeNoticeTitle, String writeNoticeDetail) {
		adminDao.writeAdminNotice(writeNoticeTitle, writeNoticeDetail);
	}
	
	// QnA 쓰기
	public void writeAdminQnA(String writeUserQuestionName, String writeUserQuestionText,
			  String writeAdminAnswerName, String writeAdminAnswerText) {
		adminDao.writeAdminQnA(writeUserQuestionName, writeUserQuestionText, writeAdminAnswerName, writeAdminAnswerText);
}
	
	public void modifyAdminExercise(int itemId, String modifyName, String modifyLocation, 
									String modifyKind, String modifyLink, int modifyLike, int modifyBmiId) {
		adminDao.modifyAdminExercise(itemId, modifyName, modifyLocation, modifyKind, modifyLink, modifyLike, modifyBmiId);
	}
	
	public void modifyAdminFood(int itemId, String modifyFoodName, int modifyFoodKal, 
								int modifyFoodPro, int modifyFoodLike, int modifyFoodBmiId) {
		adminDao.modifyAdminFood(itemId, modifyFoodName, modifyFoodKal, modifyFoodPro, modifyFoodLike, modifyFoodBmiId);
	}
	
	public void modifyAdminNotice(int itemId, String modifyTitle, String modifyDetail) {
		adminDao.modifyAdminNotice(itemId, modifyTitle, modifyDetail);
	}
	
	public void modifyAdminQnA(int itemId, String modifyAdminAnswerName, String modifyAdminAnswerText) {
		adminDao.modifyAdminQnA(itemId, modifyAdminAnswerName, modifyAdminAnswerText);
	}

	public void deleteAdminSelectItem(String selectList, int itemId) {
		adminDao.deleteAdminSelectItem(selectList, itemId);
	}

	public Exercise getExercise(int itemId) {
		return adminDao.getExercise(itemId);
	}

	public Food getFood(int itemId) {
		return adminDao.getFood(itemId);
	}

	public QnABoard getQnA(int itemId) {
		return adminDao.getQnA(itemId);
	}

	public NoticeBoard getNotice(int itemId) {
		return adminDao.getNotice(itemId);
	}
}
