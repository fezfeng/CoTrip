package mydb;

public class AcceptList {

	protected int id;
	protected int user_id;
	protected int trip_id;
	
	public AcceptList(){}
	public AcceptList(int id, int user_id, int trip_id) {
		this.id = id;
		this.user_id = user_id;
		this.trip_id = trip_id;
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
	
}
