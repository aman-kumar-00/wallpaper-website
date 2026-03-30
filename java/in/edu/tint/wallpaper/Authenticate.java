package in.edu.tint.wallpaper;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Authenticate")
public class Authenticate extends HttpServlet {
	
	  private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        UserDAO dao = new UserDAO();

        boolean isAuthenticated = dao.login(email, pass);

        if (isAuthenticated) {

            HttpSession session = request.getSession();
            session.setAttribute("user", email);

            response.sendRedirect("home.html");

        } else {

            response.sendRedirect("login.html?error=1");

        }
    }
}








