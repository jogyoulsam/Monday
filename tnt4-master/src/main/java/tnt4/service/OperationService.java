package tnt4.service;

import java.util.List;

import tnt4.container.Container;
import tnt4.dao.OperationDao;
import tnt4.dto.NoticeBoard;
import tnt4.dto.QnABoard;

public class OperationService {
	private OperationDao operationDao;

	public OperationService() {
		operationDao = Container.operationDao;
	}

	public List<String> getExerciseList(String selectPlace, String selectExercise, String loginId) {
		return operationDao.getExerciseList(selectPlace, selectExercise, loginId);
	}

	public List<String> getExerciseList() {
		return operationDao.getExerciseList3();
	}

	public List<String> getExerciseList2(String selectPlace, String selectExercise, String loginId) {
		return operationDao.getExerciseList2(selectPlace, selectExercise, loginId);
	}

	public List<String> getFoodList(int num, String loginId) {
		return operationDao.getFoodList(num, loginId);
	}

	public List<NoticeBoard> getNoticeBoard() {
		return operationDao.getNoticeBoard();
	}

	public List<QnABoard> getQnABoard() {
		return operationDao.getQnABoard();
	}

	public void likeExercise(int id, String loginId, String what) {
		operationDao.upLikeExercise(id, loginId, what);
	}

	public void likeFood(int id, String loginId, String what) {
		operationDao.upLikeFood(id, loginId, what);
	}

	public void userWriteQnA(String userWriteQnAName, String userWriteQnAText) {
		operationDao.userWriteQnA(userWriteQnAName, userWriteQnAText);
	}
}