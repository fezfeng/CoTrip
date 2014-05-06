package mydb;

import java.sql.Date;

public class Trip {

	protected int id;
	protected int place_id;
	protected String trip_name;
	protected Date date;
	
	public Trip(){}
	public Trip(int id, int place_id, String trip_name, Date date) {
		this.id = id;
		this.place_id = place_id;
		this.trip_name = trip_name;
		this.date = date;
	}
	
	public int getId() {
		return id;
	}
	
	public void setPlaceId(int place_id) {
		this.place_id = place_id;
	}
	public int getPlaceId() {
		return place_id;
	}
	
	public void setTripName(String trip_name) {
		this.trip_name = trip_name;
	}
	public String getTripName() {
		return trip_name;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return date;
	}
	
}
