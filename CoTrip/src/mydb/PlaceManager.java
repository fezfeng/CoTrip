package mydb;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PlaceManager {

	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;

	String createPlaceSql = "INSERT INTO PLACE (NAME, TYPE, ADDRESS, URL, RATING) VALUES (?, ?, ?, ?, ?);";
	String readPlaceForIDSql = "SELECT * FROM PLACE WHERE ID=?;";
	String deletePlaceSqlForIDSql = "DELETE FROM PLACE WHERE ID=?";
	String readPlaceForNameSql = "SELECT * FROM PLACE WHERE NAME=?;";

	public void createPlace(Place place) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(createPlaceSql);
			statement.setString(1, place.getName());
			statement.setString(2, place.getType());
			statement.setString(3, place.getAddress());
			statement.setString(4, place.getURL());
			statement.setFloat(5, place.getRating());
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
	

	public Place readPlaceForID(int place_id) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readPlaceForIDSql);
			statement.setInt(1, place_id);
			results = statement.executeQuery();
			if(results.next()) {
				Place place = new Place(results.getInt("id"), 
						results.getString("name"), results.getString("type"), 
						results.getString("address"), results.getString("url"), 
						results.getFloat("rating"));
				return place;
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
	
	public Place readPlaceForName(String place_name) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readPlaceForNameSql);
			statement.setString(1, place_name);
			results = statement.executeQuery();
			if(results.next()) {
				Place place = new Place(results.getInt("id"), 
						results.getString("name"), results.getString("type"), 
						results.getString("address"), results.getString("url"), 
						results.getFloat("rating"));
				return place;
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
	
	public void deletePlaceSqlForID(int place_id) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(deletePlaceSqlForIDSql);
			statement.setInt(1, place_id);
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

	DataSource ds;
	public PlaceManager() {
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/app");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}