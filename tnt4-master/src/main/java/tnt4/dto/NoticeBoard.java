package tnt4.dto;

public class NoticeBoard {
	private int id;
    private String title;
    private String detail;

    public NoticeBoard(int id, String title, String detail) {
        this.id = id;
        this.title = title;
        this.detail = detail;
    }
    
    public NoticeBoard(int id, String title) {
        this.id = id;
        this.title = title;
    }
	public String getTitle() {
		return title;
	}
    
    public String getDetail() {
        return detail;
    }
    
    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    public String toString() {
        return "No." + id + " / 제목 : " + title + "\n" + detail;
    }

	public int getId() {
		return id;
	}
}
