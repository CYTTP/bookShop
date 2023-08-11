package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet("/UserAllServlet")
public class UserAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("进入获取全部用户列表");
		resp.setHeader("Access-Control-Allow-Origin", "*"); // 星号表示所有的域都可以接受
		resp.setHeader("Access-Control-Allow-Methods", "*");
		resp.setContentType("application/json;charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		//
		if (!LoginServlet.authentication(req, resp)) {
			Map<String, Object> maps = new HashMap<>();
			maps.put("message", "未授权，请先登录");
			resp.setStatus(401);
			resp.getWriter().write(JSON.toJSONString(maps));
			return;
		}

		List<User> users = null;
		try {
			users = UserService.userService.selectAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Map<String, Object> maps = new HashMap<>();
		maps.put("data", users);
		resp.getWriter().print(JSON.toJSONString(maps));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("用户添加");
		resp.setHeader("Access-Control-Allow-Origin", "*"); // 星号表示所有的域都可以接受
		resp.setHeader("Access-Control-Allow-Methods", "*");
		resp.setContentType("application/json;charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("UTF-8");
		if (!LoginServlet.authentication(req, resp)) {
			Map<String, Object> maps = new HashMap<>();
			maps.put("message", "未授权，请先登录");
			resp.setStatus(401);
			resp.getWriter().write(JSON.toJSONString(maps));
			return;
		}

		req.setCharacterEncoding("utf-8");
		// 1.获取前端页面中的表单数据
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String telephone = req.getParameter("telephone");

		if (username == null || password == null || gender == null || telephone == null) {
			User user = new User(username, password, gender, telephone);
			System.out.println(user);
			resp.setStatus(500);
			resp.getWriter().write("{\"message\":\"添加失败,参数不能为空\"}");
			return;
		}
		User user = new User(username, password, gender, telephone);
		System.out.println(user);
		try {
			User add = UserService.userService.add(user);
			if (add != null) {
				System.out.println("添加成功");
				resp.getWriter().write("{\"message\":\"添加成功\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(500);
			resp.getWriter().write("{\"message\":\"添加失败\"}");
		}

	}

	/**
	 * 修改 done.
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("进入修改学生");
		resp.setHeader("Access-Control-Allow-Origin", "*"); // 星号表示所有的域都可以接受
		resp.setHeader("Access-Control-Allow-Methods", "*");
		resp.setContentType("application/json;charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
//
//		if (!LoginServlet.authentication(req, resp)) {
//			Map<String, Object> maps = new HashMap<>();
//			maps.put("message", "未授权，请先登录");
//			resp.setStatus(401);
//			resp.getWriter().write(JSON.toJSONString(maps));
//			return;
//		}

		req.setCharacterEncoding("utf-8");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String telephone = req.getParameter("telephone");

//		if (username == null || password == null || gender == null || telephone == null) {
//			User user = new User(username, password, gender, telephone);
//			System.out.println(user);
//			resp.setStatus(500);
//			resp.getWriter().write("{\"message\":\"修改失败,参数不能为空\"}");
//			return;
//		}

		User user = new User(username, password, gender, telephone);
		System.out.println(user);
		try {
			User upuser = UserService.userService.update(user);
			if (upuser != null) {
				Map<String, Object> maps = new HashMap<>();
				maps.put("message", "修改成功");
				maps.put("data", upuser);
				System.out.println("修改成功");
				resp.getWriter().write(JSON.toJSONString(maps));
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(500);
			resp.getWriter().write("{\"message\":\"修改失败\"}");
		}

	}

	/**
	 * done. 删除
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("进入删除学生");
		resp.setHeader("Access-Control-Allow-Origin", "*"); // 星号表示所有的域都可以接受
		resp.setHeader("Access-Control-Allow-Methods", "*");
		resp.setContentType("application/json;charset=UTF-8");
		resp.setCharacterEncoding("utf-8");

		if (!LoginServlet.authentication(req, resp)) {
			Map<String, Object> maps = new HashMap<>();
			maps.put("message", "未授权，请先登录");
			resp.setStatus(401);
			resp.getWriter().write(JSON.toJSONString(maps));
			return;
		}

		req.setCharacterEncoding("utf-8");

		String userName = req.getParameter("username");
		String usN = "";
		try {
			usN = UserService.userService.delete(userName);
			if (!usN.equals("")) {
				Map<String, Object> maps = new HashMap<>();
				maps.put("message", "删除成功");
				maps.put("data", usN);
				System.out.println("删除成功");
				resp.getWriter().write(JSON.toJSONString(maps));
			}
		} catch (Exception throwables) {
			throwables.printStackTrace();
			resp.setStatus(500);
			resp.getWriter().write("{\"message\":\"删除失败\"}");
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
}
