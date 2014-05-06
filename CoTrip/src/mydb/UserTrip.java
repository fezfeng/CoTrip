package mydb;

public class UserTrip {

	protected int id;
	protected int user_id;
	protected int trip_id;
	protected String comment;
	protected int score;
	
	public UserTrip(){}
	public UserTrip(int id, int user_id, int trip_id, String comment, int score) {
		this.id = id;
		this.user_id = user_id;
		this.trip_id = trip_id;
		this.comment = comment;
		this.score = score;
	}
	
	public int getId() {
		return id;
	}
	
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	public int getUserId() {
		return user_id;
	}
	
	public void setTripId(int trip_id) {
		this.trip_id = trip_id;
	}
	public int getTripId() {
		return trip_id;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getComment() {
		return comment;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	public int getScore() {
		return score;
	}
	
}