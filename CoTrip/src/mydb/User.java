package mydb;

public class User {

	protected int id;
	protected String username;
	protected String password;
	protected String city;
	
	public User(){}
	public User(int id, String username, String password, String city) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.city = city;
	}
	
	public int getId() {
		return id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity() {
		return city;
	}
	
}
