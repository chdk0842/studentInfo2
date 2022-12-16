package ch09;

import java.sql.Date;

//엔티티 클래스는 데이터 베이스와 대응된다.
public class Student {
	private int id;
	private String username;
	private String univ;
	private Date birth;
	// Date import 종류 2개 중 sql을 import해야함
	private String email;

	// 데이터를 받아주는 역할이므로 get,set 만들어주기
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUniv() {
		return univ;
	}

	public void setUniv(String univ) {
		this.univ = univ;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
