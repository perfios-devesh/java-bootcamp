package com.trial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlayerDao {
	public int savePlayer(Player player) {
		Statement st = null;
		try {
			String sql = "insert into cricket(name, matches, total_score, wickets, ducks, player_type, average_score) values('"
					+ player.getName() + "'," + player.getMatches() + "," + player.getTotalScore() + ","
					+ player.getWickets() + "," + player.getDucks() + ",'" + player.getPlayerType() + "',"+(player.getTotalScore() / player.getMatches()) +" )";
			st = UtilConnection.getConnection().createStatement();
			return st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int update(Player player) {
		int status = 0;
		try {
			Connection con = UtilConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"update cricket set name=?,matches=?,total_score=?,wickets=?, ducks=? , player_type=? , average_score=? where id=?");
			ps.setString(1, player.getName());
			ps.setInt(2, player.getMatches());
			ps.setInt(3, player.getTotalScore());
			ps.setInt(4, player.getWickets());
			ps.setInt(5, player.getDucks());
			ps.setString(6, player.getPlayerType());
			player.setAverageScore(player.getTotalScore()/ player.getMatches());
			ps.setDouble(7, player.getAverageScore());

			ps.setInt(8, player.getId());
			status = ps.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public static int delete(int id) {
		int status = 0;
		try {
			Connection con = UtilConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from cricket where id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public static Player getPlayerById(int id) {
		Player player = new Player();

		try {
			Connection con = UtilConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from cricket where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				player.setId(rs.getInt(1));
				player.setName(rs.getString(2));
				player.setMatches(rs.getInt(3));
				player.setTotalScore(rs.getInt(4));
				player.setWickets(rs.getInt(5));
				player.setDucks(rs.getInt(6));
				player.setPlayerType(rs.getString(7));
				player.setAverageScore(rs.getDouble(8));
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return player;
	}

	public static List<Player> getAllPlayers() {
		List<Player> list = new ArrayList<Player>();

		try {
			Connection con = UtilConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from cricket");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Player player = new Player();
				player.setId(rs.getInt(1));
				player.setName(rs.getString(2));
				player.setMatches(rs.getInt(3));
				player.setTotalScore(rs.getInt(4));
				player.setWickets(rs.getInt(5));
				player.setDucks(rs.getInt(6));
				player.setPlayerType(rs.getString(7));
				player.setAverageScore(rs.getDouble(8));
				list.add(player);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
