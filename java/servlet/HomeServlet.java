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
import jakarta.servlet.http.HttpSession;
import model.Wallpaper;

@WebServlet("/Home")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	

		System.out.println("called");

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		/* Pagination */

		int page = 1;

		String pageStr = request.getParameter("page");

		if (pageStr != null) {

			page = Integer.parseInt(pageStr);

		}

		int limit = 8;

		int offset = (page - 1) * limit;

		/* Category */

		String categoryStr = request.getParameter("categoryId");

		int categoryId = 0;

		if (categoryStr != null) {

			categoryId = Integer.parseInt(categoryStr);

		}

		/* Search Tags */

		String tagStr = request.getParameter("tags");

		if (tagStr == null) {

			tagStr = "";

		}

		System.out.println("Category: " + categoryId + " Tag: " + tagStr);

		/* DAO */

		WallpaperDAO dao = new WallpaperDAO();

		List<Wallpaper> list = dao.getWallpapers(tagStr, categoryId, limit, offset);

		/* Print cards */
		
		for (Wallpaper w : list) {

			out.println("<div class='col-lg-3 col-md-4 col-sm-6'>");

			out.println("<div class='card wallpaper-card shadow'>");

			/* Image wrapper */

			out.println("<div class='img-wrapper'>");

			out.println("<img loading='lazy' src='" +
			        w.getImagePath() +
			        "' class='wallpaper-img'>");

			out.println("</div>");

			/* Download button */

			out.println("<a href='" +
			        w.getImagePath() +
			        "' download class='download-btn'>");

			out.println("<img src='Icon/file.png' class='download-icon'>");

			out.println("</a>");

			out.println("</div>");

			out.println("</div>");

			}

	}
}