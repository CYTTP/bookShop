package com.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.cn.Order;
import com.google.gson.Gson;
import com.utils.DButils;

/**
 *  程淋  0410190215
 * 
 * @param 
 * @return
 * @throws 
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("获取订单信息");
		resp.setHeader("Access-Control-Allow-Origin", "*");  //  星号表示所有的域都可以接受
        resp.setHeader("Access-Control-Allow-Methods", "*");      
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
        
        String sql="select * from orders";
        System.out.println(sql);
		ResultSet oSet= DButils.getRS(sql);
		
		List<Order> Orders = new ArrayList<>();
		try {
			while(oSet.next()){
				Order p = new Order();
				p.setGuest_name(oSet.getString("guest_name"));
				p.setGuest_address(oSet.getString("guest_address"));
				p.setUser_id(oSet.getInt("user_id"));
				p.setMoney(oSet.getInt("money"));
				p.setPay_state(oSet.getString("pay_state"));
				p.setGuest_phone(oSet.getInt("guest_phone"));
				p.setOrder_time(oSet.getTime("order_time"));
				Orders.add(p);
			}
			Gson gson= new Gson();
			String json = gson.toJson(Orders);
			resp.getWriter().println(json);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
        
        
	}
	public void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("options请求");
        resp.setHeader("Access-Control-Allow-Origin", "*");  //  星号表示所有的域都可以接受
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
