package tnt4.dto;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class Exercise extends Dto{
	public String name, location, kind, link;
	public int id, bmiId;
	public int like;

	public Exercise(int id, int bmiId, int like, String name, String location, String kind, String link) {
		this.id = id;
		this.bmiId = bmiId;
		this.like = like;
		this.name = name;
		this.location = location;
		this.kind = kind;
		this.link = link;
	}
	
	public Exercise(int id, String name) {
        this.id = id;
        this.name = name;
    }

	public Exercise(Map<String, Object> row) {
		    super(row);
	        this.id = (int) row.get("id");
	        this.bmiId = (Integer) row.get("bmiId");
	        this.like = (int) row.get("like");
	        this.name = (String) row.get("name");
	        this.location = (String) row.get("location");
	        this.kind = (String) row.get("kind");
	        this.link = (String) row.get("link");
	}

	public Exercise(int itemId, String name, String location, String kind, String link, int like, int bmiId) {
		this.id = itemId;
		this.name = name;
		this.location = location;
		this.kind = kind;
		this.link = link;
		this.like = like;
		this.bmiId = bmiId;
	}
}