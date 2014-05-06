package mydb;

public class Place {

	protected int id;
	protected String name;
	protected String type;
	protected String address;
	protected String url;
	protected float rating;
	
	public Place(){}
	public Place(int id, String name, String type, String address,
			    String url, float rating) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.address = address;
		this.url = url;
		this.rating = rating;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	
	public void setURL(String url) {
		this.url = url;
	}
	public String getURL() {
		return url;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
	public float getRating() {
		return rating;
	}
	
}
