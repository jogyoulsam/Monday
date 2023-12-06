package tnt4.dto;

public class QnABoard {
	private int id;
	private String userQuestionName, userQuestionText, adminAnswerName, adminAnswerText;

	public QnABoard(int id, String userQuestionName, String userQuestionText, String adminAnswerName, String adminAnswerText) {
	        this.id = id;
	        this.userQuestionName = userQuestionName;
	        this.userQuestionText = userQuestionText;
	        this.adminAnswerName = adminAnswerName;
	        this.adminAnswerText = adminAnswerText;
	}
	
	public QnABoard(int id, String userQuestionName) {
        this.id = id;
        this.userQuestionName = userQuestionName;
    }

	public String toString() {
		return "Q." + id + " / 제목 : " + userQuestionName;
	}
	
	public String getUserQuestionName() {
		return userQuestionName;
	}
	
	public String getUserQuestionText() {
		return userQuestionText;
	}
	
	public String getAdminAnswerName() {
		return adminAnswerName;
	}
	
	public String getAdminAnswerText() {
		return adminAnswerText;
	}

	public int getId() {
		return id;
	}
}
