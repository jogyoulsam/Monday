package tnt4.dto;

import lombok.Data;

@Data
public class Admin {
	private int id; // 관리자 아이디
    private String name; // 관리자 이름
    
    public Admin(int id, String name) {
    	this.id = id;
    	this.name = name;
   	}
}
