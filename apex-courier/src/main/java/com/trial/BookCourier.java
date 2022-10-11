package com.trial;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BookCourier")
public class BookCourier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		String name = request.getParameter("name");
		long mobile = Long.parseLong(request.getParameter("mobile"));
		String fromCity = request.getParameter("fromCity");
		String toCity = request.getParameter("toCity");
		double weight = Double.parseDouble(request.getParameter("weight"));
		double cost = Double.parseDouble(request.getParameter("cost"));
		
		
		out.print(cost);
		
		CourierDetails courierDetails = new CourierDetails();
		courierDetails.setName(name);
		courierDetails.setMobile(mobile);
		courierDetails.setFromCity(fromCity);
		courierDetails.setToCity(toCity);
		courierDetails.setWeight(weight);
		courierDetails.setCost(cost);
		courierDetails.setStatus(Utils.SENT_STATUS);
		
		int state =  CourierDetailsDao.saveCourierDetails(courierDetails);
		if(state == 1) {
			response.sendRedirect("allCouriers.jsp");
		}
	}

}
