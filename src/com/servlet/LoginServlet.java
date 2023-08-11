package com.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

/**
 *  ����  0410190215
 * 
 * @param 
 * @return
 * @throws 
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final String TOKEN = "JAVAISTHEBESTINTHEWORLD";

	private static final String USERNAME = "admin";

	private static final String PASSWORD = "123456";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("�����¼");
        resp.setHeader("Access-Control-Allow-Origin", "*");  //  �Ǻű�ʾ���е��򶼿��Խ���
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Map<String,Object> maps = new HashMap<>();
        if (!isCorrect(username,password)){
            resp.setStatus(403); // ��Ȩʧ��
            maps.put("message","�û������������");
            resp.getWriter().write(JSON.toJSONString(maps));
            return;
        }
        resp.setStatus(200);
        maps.put("message","��¼�ɹ�");
        maps.put("token",TOKEN);
        resp.getWriter().write(JSON.toJSONString(maps));
	}
	/**
     * �û�������У��
     * @param username
     * @param password
     * @return
     */
    private boolean isCorrect(String username,String password){
        if(username == null || password == null ){
            return false;
        }
        if(username.equals("")||password.equals("")){
            return false;
        }
        if(username.isEmpty()||password.isEmpty()){
            return false;
        }

        return USERNAME.equals(username)&&PASSWORD.equals(password);
    }

    @Override
    public void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("options����");
        resp.setHeader("Access-Control-Allow-Origin", "*");  //  �Ǻű�ʾ���е��򶼿��Խ���
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
    }

    /**
     * Ȩ��У��
     * @param req
     * @param reps
     * @return
     */
    public static  boolean authentication(HttpServletRequest req,HttpServletResponse reps){

        String authentication = req.getHeader("Authentication");
        if(authentication==null || authentication.isEmpty()){
            return false;
        }
        return LoginServlet.TOKEN.equals(authentication);
    }
}