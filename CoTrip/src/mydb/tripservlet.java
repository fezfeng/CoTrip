package mydb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

/**
 * Servlet implementation class tripservlet
 */
@WebServlet("/tripservlet")
public class tripservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tripservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// check input
		String place_name = request.getParameter( "place_name" );
		String place_type = request.getParameter( "place_type" );
		String address  = request.getParameter( "address" );
		String place_url  = request.getParameter( "place_url" );
		String rating  = request.getParameter( "rating" );
		String username = request.getParameter( "username" );
		
		String trip_year = request.getParameter( "trip_year" );
		String trip_month = request.getParameter( "trip_month" );
		String trip_day = request.getParameter( "trip_day" );
		
		String comment = request.getParameter( "comment" );
		String trip_name = request.getParameter( "trip_name" );
		
		String score = request.getParameter( "score" );
		
		String[] invite_list = request.getParameterValues( "invite_list" );
		
		// Begin to insert
		
		// insert place first
		PlaceManager PM = new PlaceManager();
		Place place = new Place();
		place = PM.readPlaceForName(place_name);
		if (place == null) {
			place = new Place();
			place.setName(place_name);
			place.setType(place_type);
			place.setAddress(address);
			place.setURL(place_url);
			place.setRating(Float.parseFloat(rating));
			PM.createPlace(place);
			place = PM.readPlaceForName(place_name);
		}
		int place_id = place.getId();
		
		// insert trip
		java.sql.Date date = new Date(Integer.parseInt(trip_year)-1900,Integer.parseInt(trip_month)-1,Integer.parseInt(trip_day));
		
		Trip trip = new Trip();
		TripManager TM = new TripManager();
		trip = TM.readTripForTripName(trip_name);
		if(trip == null) {
			trip = new Trip();
			trip.setPlaceId(place_id);
			trip.setTripName(trip_name);
			trip.setDate(date);
			TM.createTrip(trip);
			trip = TM.readTripForTripName(trip_name);
		} else {
			// error happens, tripname already exist
			request.getRequestDispatcher("trip.jsp").forward(request, response);
			
			return;
		}
		int trip_id = trip.getId();
		
		// insert user_trip
		User user = new User();
		UserManager UM = new UserManager();
		user = UM.getUserForName(username);
		if (user == null) {
			// error happens, user not found
			response.sendRedirect("index.jsp");
		}
		int user_id = user.getId();
		
		UserTrip user_trip = new UserTrip(0, user_id, trip_id, comment, Integer.parseInt(score));
		UserTripManager UTM = new UserTripManager();
		UTM.createUserTrip(user_trip);
		user_trip = UTM.readUserTripForUserAndTrip(user_id, trip_id);
		if (user_trip == null) {
			response.sendRedirect("index.jsp");
		}
		int user_trip_id = user_trip.getId();
		
		// insert accept list
		if (invite_list != null) {
			for(int i=0; i<invite_list.length; i++)
			{
				user = UM.getUserForName(invite_list[i]);
				if (user == null) {
					response.sendRedirect("index.jsp");
				}
				AcceptList accept_list = new AcceptList(0, user.getId(), trip_id);
				AcceptListManager AM = new AcceptListManager();
				AM.createAcceptList(accept_list);
			}
		}
		
		request.getSession().setAttribute("user",username);
		
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

}
