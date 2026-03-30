package in.edu.tint.wallpaper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;

import java.io.IOException;

import dao.UserDAO;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String username = request.getParameter("UserName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setUserName(username);
        user.setEmail(email);
        user.setPassword(password);

        UserDAO dao = new UserDAO();

        boolean status = dao.signup(user);

        if(status){
        	
        	 HttpSession session = request.getSession();

        	    session.setAttribute("user", email);

            response.sendRedirect("home.html");

        }else{

            response.getWriter().println("Signup failed user exists please login");

        }
    }
}
