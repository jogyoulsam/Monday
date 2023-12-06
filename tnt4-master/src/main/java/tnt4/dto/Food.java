package tnt4.dto;

import java.util.Map;

public class Food extends Dto{
	public String name;
	public int id, bmiId , like , kal , pro ;

	public Food(int id,int bmiId, int like, String name,int kal, int pro) {
		this.id = id;
		this.bmiId = bmiId;
		this.like = like;
		this.name = name;
		this.kal = kal;
		this.pro = pro;
	}
	
	public Food(int id, String name) {
        this.id = id;
        this.name = name;
    }
	
	public Food(Map<String, Object> row) {
		    super(row);
	        this.id = (int) row.get("id");
	        this.bmiId = (Integer) row.get("bmiId");
	        this.like = (int) row.get("like");
	        this.name= (String) row.get("name");
	        this.kal= (int) row.get("kal");
	        this.pro = (int) row.get("pro");
	}
	
	public Food(int itemId, String name, int kal, int pro, int like, int bmiId) {
		this.id = itemId;
		this.name = name;
		this.kal = kal;
		this.pro = pro;
		this.like = like;
		this.bmiId = bmiId;
	}

	@Override
    public String toString() {
        return "식단 ID : " + id + ", 이름 : " + name;
    }

	public String getName() {
		return name;
	}
	public int getKal() {
		return kal;
	}
	public int getPro() {
		return pro;
	}
	public int getLike() {
		return like;
	}
	public int getBmiId() {
		return bmiId;
	}
}