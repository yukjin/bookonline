package bookOnline;

import java.io.Serializable;

public class User implements Serializable
{
	private int userId;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String address;
	private int userState;
	private String profession;
	public User(){}
	public User(String username,String password,String email,String address,int userState,String profession)
	{
		this.username=username;
		this.password=password;
		this.email=email;
		this.address=address;
		this.userState=userState;
		this.profession=profession;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getUserState() {
		return userState;
	}
	public void setUserState(int userState) {
		this.userState = userState;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
