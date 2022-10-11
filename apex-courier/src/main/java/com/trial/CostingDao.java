package com.trial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CostingDao {

	public static List<Costing> getAllCostings() {
		List<Costing> list = new ArrayList<Costing>();

		try {
			Connection con = Utils.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from costing");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Costing costing = new Costing();
				costing.setId(rs.getInt(1));
				costing.setFromCity(rs.getString(2));
				costing.setToCity(rs.getString(3));
				costing.setDistance(rs.getInt(4));
				costing.setCostPerGram(rs.getDouble(5));
				list.add(costing);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
}
