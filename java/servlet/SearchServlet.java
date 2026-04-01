package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.WallpaperDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.Wallpaper;

@WebServlet("/Search")

public class SearchServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		/* Get search keyword */

		String tagStr = request.getParameter("tags");

		String categoryStr = request.getParameter("categoryId");

		int categoryId = 1;

		if (categoryStr != null) {

			categoryId = Integer.parseInt(categoryStr);

		}

		/* Pagination */

		int page = 1;

		String pageStr = request.getParameter("page");

		if (pageStr != null) {

			page = Integer.parseInt(pageStr);

		}

		int limit = 8;

		int offset = (page - 1) * limit;

		/* DAO */

		WallpaperDAO dao = new WallpaperDAO();

		List<Wallpaper> list = dao.getWallpapers(tagStr, categoryId, limit, offset);

		/* Print cards */

		for (Wallpaper w : list) {

			out.println("<div class='col-lg-3 col-md-4 col-sm-6'>");

			out.println("<div class='card shadow h-100 position-relative'>");

			out.println("<img src='" + w.getImagePath() + "' class='wallpaper-img'>");

			out.println("<a href='" + w.getImagePath() + "' download class='download-btn'>");

			out.println("<img src='Icon/file.png' class='download-icon'>");

			out.println("</a>");

			out.println("</div>");

			out.println("</div>");

		}

	}

}