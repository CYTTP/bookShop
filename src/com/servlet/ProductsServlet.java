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

import com.cn.Products;
import com.google.gson.Gson;
import com.utils.DButils;

/**
 *  程淋  0410190215
 * 
 * @param 
 * @return
 * @throws 
 */
@WebServlet("/ProductsServlet")
public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("获取商品信息");
		resp.setHeader("Access-Control-Allow-Origin", "*");  //  星号表示所有的域都可以接受
        resp.setHeader("Access-Control-Allow-Methods", "*");      
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
        
        String sql = "select * from products" ;
		System.out.println(sql);
		ResultSet pro= DButils.getRS(sql);
		
		List<Products> products = new ArrayList<>();
		try {
			while(pro.next()){
				Products p = new Products();
				p.setId(pro.getInt("id"));
				p.setName(pro.getString("name"));
				p.setCategory(pro.getString("category"));
				p.setDescription(pro.getString("description"));
				p.setPrice(pro.getInt("price"));
				p.setPnum(pro.getInt("pnum"));
				products.add(p);
			}
			Gson gson= new Gson();
			String json = gson.toJson(products);
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
