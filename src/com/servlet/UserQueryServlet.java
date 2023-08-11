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
 *  ����  0410190215
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
		    System.out.println("�����ѯ");
	        resp.setHeader("Access-Control-Allow-Origin", "*");  //  �Ǻű�ʾ���е��򶼿��Խ���
	        resp.setHeader("Access-Control-Allow-Methods", "*");
	        resp.setHeader("Access-Control-Allow-Headers", "*");
	        resp.setContentType("application/json;charset=UTF-8");
	        resp.setCharacterEncoding("utf-8");
	        
	        
	        
	        if (!LoginServlet.authentication(req,resp)){
	            Map<String,Object> maps = new HashMap<>();
	            maps.put("message","δ��Ȩ�����ȵ�¼");
	            resp.setStatus(401);
	            resp.getWriter().write(JSON.toJSONString(maps));
	            return;
	        }
	        
	       
	        List<User> users=null;
	        try {
	            System.out.println("======"+req.getParameter("gender"));
	            System.out.println("======"+req.getParameter("username"));

	            if(req.getParameter("gender")!=null) { // ��������ѯ
	            	users = UserService.userService.selectByGender(req.getParameter("gender"));
	            }else if(req.getParameter("username")!=null){ // ���û�����ѯ
	            	users = UserService.userService.selectByUserName(req.getParameter("username"));
	            }else {
	                resp.setStatus(500);
	                resp.getWriter().write("{\"message\":\"�Ƿ���ѯ\"}");
	                System.out.println("�Ƿ���ѯ");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            resp.setStatus(500);
	            resp.getWriter().write("{\"message\":\"��ѯʧ�ܣ����ݿ��쳣\"}");
	        }
	        Map<String,Object> maps = new HashMap<>();
	        maps.put("message","��ѯ�ɹ�");
	        maps.put("data",users);
	        System.out.println("��ѯ�ɹ�");
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
        System.out.println("options����");
        resp.setHeader("Access-Control-Allow-Origin", "*");  //  �Ǻű�ʾ���е��򶼿��Խ���
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
    }

}
