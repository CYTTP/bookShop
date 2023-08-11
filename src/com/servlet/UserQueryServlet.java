package com.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.cn.User;
import com.service.UserService;

/**
 *  程淋  0410190215
 * 
 * @param 
 * @return
 * @throws 
 */
@WebServlet("/UserQueryServlet")
public class UserQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		    System.out.println("进入查询");
	        resp.setHeader("Access-Control-Allow-Origin", "*");  //  星号表示所有的域都可以接受
	        resp.setHeader("Access-Control-Allow-Methods", "*");
	        resp.setHeader("Access-Control-Allow-Headers", "*");
	        resp.setContentType("application/json;charset=UTF-8");
	        resp.setCharacterEncoding("utf-8");
	        
	        
	        
	        if (!LoginServlet.authentication(req,resp)){
	            Map<String,Object> maps = new HashMap<>();
	            maps.put("message","未授权，请先登录");
	            resp.setStatus(401);
	            resp.getWriter().write(JSON.toJSONString(maps));
	            return;
	        }
	        
	       
	        List<User> users=null;
	        try {
	            System.out.println("======"+req.getParameter("gender"));
	            System.out.println("======"+req.getParameter("username"));

	            if(req.getParameter("gender")!=null) { // 按姓名查询
	            	users = UserService.userService.selectByGender(req.getParameter("gender"));
	            }else if(req.getParameter("username")!=null){ // 按用户名查询
	            	users = UserService.userService.selectByUserName(req.getParameter("username"));
	            }else {
	                resp.setStatus(500);
	                resp.getWriter().write("{\"message\":\"非法查询\"}");
	                System.out.println("非法查询");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            resp.setStatus(500);
	            resp.getWriter().write("{\"message\":\"查询失败，数据库异常\"}");
	        }
	        Map<String,Object> maps = new HashMap<>();
	        maps.put("message","查询成功");
	        maps.put("data",users);
	        System.out.println("查询成功");
	        resp.getWriter().write(JSON.toJSONString(maps));
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
