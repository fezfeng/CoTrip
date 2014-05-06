package mydb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TripManager {

	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	String createTripSql = "INSERT INTO TRIP (PLACE_ID, TRIP_NAME, TRIP_DATE) VALUES (?, ?, ?);";
	String readTripForTripNameSql = "SELECT * FROM TRIP WHERE TRIP_NAME=?";
	String readTripForIdSql = "SELECT * FROM TRIP WHERE ID=?";
	
	String readRecentTripSql = "SELECT * FROM TRIP ORDER BY ID DESC LIMIT 5;";
	
	String readAllTripsSql = "SELECT * FROM TRIP";
			
	public void createTrip(Trip newTrip) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(createTripSql);
			statement.setInt(1, newTrip.getPlaceId());
			statement.setString(2, newTrip.getTripName());
			Date date = new Date(newTrip.getDate().getTime());
			statement.setDate(3, date);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<Trip> readAllTrips() {
		List<Trip> trips = new ArrayList<Trip>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllTripsSql);
			results = statement.executeQuery();
			while(results.next()) {
				Trip trip = new Trip(results.getInt("id"), 
						results.getInt("place_id"), results.getString("trip_name"), 
						results.getDate("date"));
				trips.add(trip);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return trips;
	}
	
	
	public List<Trip> readRecentTrips() {
		List<Trip> trips = new ArrayList<Trip>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readRecentTripSql);
			results = statement.executeQuery();
			while(results.next()) {
				System.out.println("loop + 1 ");
				Trip trip = new Trip(results.getInt("id"), 
						results.getInt("place_id"), results.getString("trip_name"), 
						results.getDate("date"));
				trips.add(trip);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("last chance agian " + trips.size());
		return trips;
	}
	
	public Trip readTripForTripName(String trip_name) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readTripForTripNameSql);
			statement.setString(1, trip_name);
			results = statement.executeQuery();
			if(results.next()) {
				Trip trip = new Trip(results.getInt("id"), results.getInt("place_id"),
						             results.getString("trip_name"), results.getDate("trip_date"));
				return trip;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	
	public Trip readTripForId(int trip_id) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readTripForIdSql);
			statement.setInt(1, trip_id);
			results = statement.executeQuery();
			if(results.next()) {
				Trip trip = new Trip(results.getInt("id"), results.getInt("place_id"),
						             results.getString("trip_name"), results.getDate("trip_date"));
				return trip;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	DataSource ds;
	public TripManager() {
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/app");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}