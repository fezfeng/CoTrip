package mydb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AcceptServlet
 */
@WebServlet("/AcceptServlet")
public class AcceptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptServlet() {
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
		String accept = request.getParameter( "accept" );
		String score = request.getParameter( "score" );
		String comment = request.getParameter( "comment" );
		
		int accept_id = Integer.parseInt(accept);
		boolean positive = true;
		
		if (accept_id < 0) {
			accept_id = 0 - accept_id;
			positive = false;
		}
		
		AcceptListManager ALM = new AcceptListManager();
		AcceptList acceptList = ALM.readAcceptListForAId(accept_id);

		// add this accept_id to user_trip table if accept_id > 0
		if (positive) {
			UserTripManager UTM = new UserTripManager();
			UserTrip userTrip = new UserTrip();
			userTrip.setUserId(acceptList.getUserId());
			userTrip.setTripId(acceptList.getTripId());
			userTrip.setScore(Integer.parseInt(score));
			userTrip.setComment(comment);
			UTM.createUserTrip(userTrip);
		}
		
		// delete this accept_id
		ALM.deleteAcceptListForUserIDandTripID(acceptList.getUserId(), acceptList.getTripId());
		
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

}
