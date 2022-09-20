package com.trial;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SaveUpdateServlet")
public class SaveUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		String name = request.getParameter("name");
		int matches = Integer.parseInt(request.getParameter("matches"));
		int totalScore = Integer.parseInt(request.getParameter("totalScore"));
		int wickets = Integer.parseInt(request.getParameter("wickets"));
		int ducks = Integer.parseInt(request.getParameter("ducks"));
		String playerType = request.getParameter("playerType");

		Player player = new Player();
		player.setId(id);
		player.setName(name);
		player.setMatches(matches);
		player.setTotalScore(totalScore);
		player.setWickets(wickets);
		player.setDucks(ducks);
		player.setPlayerType(playerType);

		int status = PlayerDao.update(player);
		if (status > 0) {
			response.sendRedirect("index.html");
		} else {
			out.println("Sorry! unable to update record");
		}

		out.close();
	}
}
