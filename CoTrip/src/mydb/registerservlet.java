package mydb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class registerservlet
 */
@WebServlet("/registerservlet")
public class registerservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerservlet() {
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
		String username = request.getParameter("username");
		UserManager um = new UserManager();
		if(um.readAllUsernames().contains(username)){
			response.getWriter().print("Username exists!");
			return;
		}
		
		String password = request.getParameter("password");
		String city = request.getParameter("city");
		User newUser = new User(1,username,password,city);
		um.createUser(newUser);
		request.setAttribute("info", "Register Succeeded!");
		
		request.getSession().setAttribute("user",username);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

}
