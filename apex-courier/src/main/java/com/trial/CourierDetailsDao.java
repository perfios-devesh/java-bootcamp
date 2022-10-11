package com.trial;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourierDetailsDao {

	public static List<CourierDetails> getAllCourierDetails() {
		List<CourierDetails> list = new ArrayList<CourierDetails>();

		try {
			Connection con = Utils.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from courier_details");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CourierDetails courierDetails = new CourierDetails();
				courierDetails.setId(rs.getInt(1));
				courierDetails.setName(rs.getString(2));
				courierDetails.setMobile(Long.valueOf(rs.getString(3)));
				courierDetails.setFromCity(rs.getString(4));
				courierDetails.setToCity(rs.getString(5));
				courierDetails.setWeight(rs.getDouble(6));
				courierDetails.setCost(rs.getDouble(7));
				courierDetails.setSendDate(rs.getDate(8));
				courierDetails.setDeliveryDate(rs.getDate(9));
				courierDetails.setStatus(rs.getString(10));
				list.add(courierDetails);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static int saveCourierDetails(CourierDetails courierDetails) {
		try {
			Date date = new Date(System.currentTimeMillis());
			System.out.println(date);
			courierDetails.setSendDate(date);
			Date delDate = new Date(System.currentTimeMillis() + 4 * 86400000);
			System.out.println(delDate);
			courierDetails.setDeliveryDate(delDate);

			PreparedStatement pst = Utils.getConnection().prepareStatement("insert into courier_details(name, mobile, from_city, to_city, weight, cost, send_date, delivery_date, status) values (?,?,?,?,?,?,?,?,?)");
			pst.setString(1, courierDetails.getName());
			pst.setString(2, String.valueOf(courierDetails.getMobile()));
			pst.setString(3, courierDetails.getFromCity());
			pst.setString(4, courierDetails.getToCity());
			pst.setDouble(5, courierDetails.getWeight());
			pst.setDouble(6, courierDetails.getCost());
			pst.setDate(7, courierDetails.getSendDate());
			pst.setDate(8, courierDetails.getDeliveryDate());
			pst.setString(9, courierDetails.getStatus());
			
			System.out.println(pst);
			return pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int setAsDelievered(int id) {
		try {
			
			PreparedStatement pst = Utils.getConnection().prepareStatement("update courier_details set status=? where id=?");
			pst.setString(1, Utils.DELIVERED_STATUS);
			pst.setInt(2, id);
			
			System.out.println(pst);
			return pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}

















