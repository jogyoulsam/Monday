package tnt4.dto;

import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class Member extends Dto {
	public int id;
    public String loginId;
    public String loginPw;
    public String name, gender;
    public int height, weight;
    public Integer bmiId; // Integer로 변경

    public Member(Map<String, Object> row) {
        super(row);
        this.loginId = (String) row.get("loginId");
        this.loginPw = (String) row.get("loginPw");
        this.name = (String) row.get("name");
        this.gender = (String) row.get("gender");
        this.height = (int) row.get("height");
        this.weight = (int) row.get("weight");
        this.bmiId = (Integer) row.get("bmiId"); // Integer로 형변환
    }

    public Member(String loginId, String loginPw, String name, String gender, Integer height, Integer weight, Integer bmiId) {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.bmiId = bmiId;
    }

	// 회원 정보 수정 할떄는 이렇게 하는 함수 추가
	// 회원 정보 수정항목때는 아이디랑 비번은 그대로이니까
	public Member(String name, String gender, int height, int weight, int bmiId) {
		this.name = name;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.bmiId = bmiId;
	}
	
	public Member(int id, String name) {
		this.id = id;
		this.name = name;
	}
}