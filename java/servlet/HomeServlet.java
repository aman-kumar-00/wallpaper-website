package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import dao.WallpaperDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Wallpaper;

@WebServlet("/Home")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
System.out.println("caalledddd");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		int page = 1;

		String pageStr = request.getParameter("page");
		
		
		

		if (pageStr != null) {
			page = Integer.parseInt(pageStr);
		}

		int limit = 8;
		int offset = (page - 1) * limit;

		WallpaperDAO dao = new WallpaperDAO();

		List<Wallpaper> list = dao.getWallpapers(limit, offset);

		for (Wallpaper w : list) {

			out.println("<div class='col-lg-3 col-md-4 col-sm-6'>");

			out.println("<div class='card shadow h-100'>");

			out.println("<img src='" + w.getImagePath() + "' class='card-img-top'>");

			out.println("<div class='card-body'>");

			out.println("<h5>" + w.getTitle() + "</h5>");

			out.println("</div>");

			out.println("</div>");

			out.println("</div>");
		}
	}
}