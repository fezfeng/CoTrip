package mydb;

public class Photo {

	protected int id;
	protected int user_trip_id;
	protected String photo;
	
	public Photo(){}
	public Photo(int id, int user_trip_id, String photo) {
		this.id = id;
		this.user_trip_id = user_trip_id;
		this.photo = photo;
	}
	
	public int getId() {
		return id;
	}
	
	public void setUserTripId(int user_trip_id) {
		this.user_trip_id = user_trip_id;
	}
	public int getUserTripId() {
		return user_trip_id;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getPhoto() {
		return photo;
	}
	
}
