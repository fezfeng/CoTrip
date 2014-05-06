package mydb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserTripManager {

	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;

	String createUserTripSql = "INSERT INTO USER_TRIP (COMMENT, SCORE, USER_ID, TRIP_ID) VALUES (?, ?, ?, ?);";
	String readUserTripForUserSql = "SELECT * FROM USER_TRIP WHERE USER_ID=?;";
	String readUserTripForTripSql = "SELECT * FROM USER_TRIP WHERE TRIP_ID=?;";
	String readUserTripForUserAndTripSql = "SELECT * FROM USER_TRIP WHERE USER_ID=? AND TRIP_ID=?;";
	
	String readMyRecentTripSql = "SELECT * FROM USER_TRIP WHERE USER_ID=? ORDER BY SCORE DESC LIMIT 10";

	public void createUserTrip(UserTrip userTrip) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(createUserTripSql);
			statement.setString(1, userTrip.getComment());
			statement.setInt(2, userTrip.getScore());
			statement.setInt(3, userTrip.getUserId());
			statement.setInt(4, userTrip.getTripId());
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

	public List<UserTrip> readUserTripForUser(User user) {
		List<UserTrip> user_trips = new ArrayList<UserTrip>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readUserTripForUserSql);
			statement.setInt(1, user.getId());
			results = statement.executeQuery();
			while(results.next()) {
				UserTrip user_trip = new UserTrip(results.getInt("id"), results.getInt("user_id"),
						results.getInt("trip_id"), results.getString("comment"), results.getInt("score"));
				user_trips.add(user_trip);
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
		return user_trips;
	}
	
	
	public List<UserTrip> readMyRecentTrips(User user) {
		List<UserTrip> user_trips = new ArrayList<UserTrip>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readMyRecentTripSql);
			statement.setInt(1, user.getId());
			results = statement.executeQuery();
			while(results.next()) {
				UserTrip user_trip = new UserTrip(results.getInt("id"), results.getInt("user_id"),
						results.getInt("trip_id"), results.getString("comment"), results.getInt("score"));
				user_trips.add(user_trip);
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
		return user_trips;
	}
	
	
	public List<UserTrip> readAllRecentTrips(List<Trip> trips) {
		List<UserTrip> user_trips = new ArrayList<UserTrip>();
		
		for(Trip t:trips) {
			user_trips.addAll(readUserTripForTrip(t));
		}
		
		return user_trips;
	}

	public List<UserTrip> readUserTripForTrip(Trip trip) {
		List<UserTrip> user_trips = new ArrayList<UserTrip>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readUserTripForTripSql);
			statement.setInt(1, trip.getId());
			results = statement.executeQuery();
			while(results.next()) {
				UserTrip user_trip = new UserTrip(results.getInt("id"), results.getInt("user_id"),
						results.getInt("trip_id"), results.getString("comment"), results.getInt("score"));
				user_trips.add(user_trip);
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
		return user_trips;
	}
	
	public UserTrip readUserTripForUserAndTrip(int user_id, int trip_id) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readUserTripForUserAndTripSql);
			statement.setInt(1, user_id);
			statement.setInt(2, trip_id);
			results = statement.executeQuery();
			if(results.next()) {
				UserTrip user_trip = new UserTrip(results.getInt("id"), results.getInt("user_id"),
						results.getInt("trip_id"), results.getString("comment"), results.getInt("score"));
				return user_trip;
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
	public UserTripManager() {
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/app");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}