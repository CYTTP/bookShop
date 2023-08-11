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

import com.cn.OrderItems;
import com.google.gson.Gson;
import com.utils.DButils;

/**
 *  程淋  0410190215
 * 
 * @param 
 * @return
 * @throws 
 */
@WebServlet("/OrderItemServlet")
public class OrderItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("获取订单详情信息");
		resp.setHeader("Access-Control-Allow-Origin", "*");  //  星号表示所有的域都可以接受
        resp.setHeader("Access-Control-Allow-Methods", "*");      
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
        
        String sql = "select * from orderitems" ;
		System.out.println(sql);
		ResultSet oritems= DButils.getRS(sql);
		
		List<OrderItems> orderItems = new ArrayList<>();
		try {
			while(oritems.next()){
				OrderItems p = new OrderItems();
				p.setOrder_id(oritems.getInt("order_id"));
				p.setProduct_id(oritems.getInt("product_id"));
				p.setBuy_num(oritems.getInt("buy_num"));
				orderItems.add(p);
			}
			Gson gson= new Gson();
			String json = gson.toJson(orderItems);
			resp.getWriter().println(json);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	public void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("options请求");
        resp.setHeader("Access-Control-Allow-Origin", "*");  //  星号表示所有的域都可以接受
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
    }

}
