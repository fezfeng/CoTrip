package mydb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AcceptListManager {

	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;

	String createAcceptListsql = "INSERT INTO ACCEPT_LIST (USER_ID, TRIP_ID) VALUES (?, ?);";
	String readAcceptListForUsersql = "SELECT * FROM ACCEPT_LIST WHERE USER_ID=?;";
	String deleteAcceptListForUsersql = "DELETE FROM ACCEPT_LIST WHERE USER_ID=?";
	String deleteAcceptListForUserIDandTripIDSql = "DELETE FROM ACCEPT_LIST WHERE USER_ID=? AND TRIP_ID=?";
	
	String readAcceptListForAIdsql = "SELECT * FROM ACCEPT_LIST WHERE ID = ?;";

	public void createAcceptList(AcceptList acceptList) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(createAcceptListsql);
			statement.setInt(1, acceptList.getUserId());
			statement.setInt(2, acceptList.getTripId());
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

	public List<AcceptList> readAcceptListForUser(User user) {
		List<AcceptList> accept_lists = new ArrayList<AcceptList>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAcceptListForUsersql);
			
			statement.setInt(1, user.getId());
			results = statement.executeQuery();
			while(results.next()) {
				AcceptList accept_list = new AcceptList(results.getInt("id"),
						                                 results.getInt("user_id"),
						                                 results.getInt("trip_id"));
				accept_lists.add(accept_list);
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
		return accept_lists;
	}
	
	public AcceptList readAcceptListForAId(int acceptlist_id) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAcceptListForAIdsql);
			
			statement.setInt(1, acceptlist_id);
			results = statement.executeQuery();
			if(results.next()) {
				AcceptList accept_list = new AcceptList(results.getInt("id"),
						                                 results.getInt("user_id"),
						                                 results.getInt("trip_id"));
				return accept_list;
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
	
	public void deleteAcceptListForUser(User user) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(deleteAcceptListForUsersql);
			statement.setInt(1, user.getId());
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
	
	public void deleteAcceptListForUserIDandTripID(int user_id, int trip_id) {
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(deleteAcceptListForUserIDandTripIDSql);
			statement.setInt(1, user_id);
			statement.setInt(2, trip_id);
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
	public AcceptListManager() {
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/app");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}