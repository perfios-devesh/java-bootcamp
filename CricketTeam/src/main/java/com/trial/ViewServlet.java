package com.trial;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<a href='index.html'>Dashboard</a>");
		out.println("<h1>All Players</h1>");

		List<Player> list = PlayerDao.getAllPlayers();

		out.print("<table border='1' width='100%'");
		out.print(
				"<tr><th>Id</th><th>Name</th><th>Matches</th><th>Score</th><th>Wickets</th><th>Ducks</th><th>Player Type</th><th>Average Score</th><th>Update</th><th>Delete</th></tr>");
		for (Player player : list) {
			out.print("<tr><td>" + player.getId() + "</td><td>" + player.getName() + "</td><td>" + player.getMatches()
					+ "</td><td>" + player.getTotalScore() + "</td><td>" + player.getWickets() + "</td><td>"
					+ player.getDucks() + "</td><td>" + player.getPlayerType() + "</td><td>"+player.getAverageScore()+"</td><td><a href='UpdateServlet?id="
					+ player.getId() + "'>Update</a></td><td><a href='DeleteServlet?id=" + player.getId()
					+ "'>Delete</a></td></tr>");
		}
		out.print("</table>");

		out.close();
	}
}
