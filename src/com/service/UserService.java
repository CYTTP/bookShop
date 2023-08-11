package com.service;
/**
 *  程淋  0410190215
 * 
 * @param 
 * @return
 * @throws 
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

import com.cn.User;
import com.utils.DButils;

public class UserService {
	public static final UserService userService = new UserService();

	/**
	 * 修改用户
	 * 
	 * @param user  程淋  0410190215
	 * @return
	 * @throws SQLException
	 */
	public User update(User user) throws SQLException {
		Connection open = DButils.getConnection();
		String sql = "update users set password=?,gender=?,telephone=? where username=?";
		PreparedStatement ps = open.prepareStatement(sql);
		ps.setString(1, user.getPassword());
		ps.setString(2, user.getGender());
		ps.setString(3, user.getTelephone());
		ps.setString(4, user.getUsername());

		ps.execute();
		// 返回新数据
		return user;
	}

	/**
	 * 添加用户
	 * 
	 * @param user 程淋  0410190215
	 * @return
	 * @throws SQLException
	 */
	public User add(User user) throws SQLException {
		Connection open = DButils.getConnection();
		// 操作数据库，实现增删改查
		String sql = "insert into users(username,password,gender,telephone) values(?,?,?,?)";
		PreparedStatement ps = open.prepareStatement(sql);
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getGender());
		ps.setString(4, user.getTelephone());

		ps.execute();
		return user;
	}
	
	

	/**
	 * 删除用户
	 * 
	 * @param username 程淋  0410190215
	 * @return
	 * @throws SQLException
	 */
	public String delete(String username) throws SQLException {
		Connection open = DButils.getConnection();
		String sql = "delete from users where username =?";
		PreparedStatement ps = open.prepareStatement(sql);
		ps.setString(1, username);
		ps.execute();
		return username;
	}

	/**
	 * 获取所有的
	 * 
	 * @return 程淋  0410190215
	 * @throws SQLException
	 */
	public List<User> selectAll() throws SQLException {
		List<User> list = new ArrayList<>();
		Connection open = DButils.getConnection();
		String sql = "select * from users";
		PreparedStatement ps = open.prepareStatement(sql);
		ResultSet resultSet = ps.executeQuery();
		while (resultSet.next()) {
			User user = new User();
			user.setUsername(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setGender(resultSet.getString("gender"));
			user.setTelephone(resultSet.getString("telephone"));
			list.add(user);
		}
		return list;
	}
	
    /**
     * 按照姓名查询
     * 模糊查询
     * done.
     * @param gender 程淋  0410190215
     * @return
     * @throws SQLException
     */
    public List<User> selectByGender(String gender) throws SQLExceptionList {
        List<User> list = new ArrayList<>();
        Connection open = DButils.getConnection();
        String sql = "select * from users where gender like ?";
        PreparedStatement ps = open.prepareStatement(sql);
        ps.setString(1,"%"+gender+"%");
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()){
        	User user = new User();
        	user.setUsername(resultSet.getString("username"));
        	user.setPassword(resultSet.getString("password"));
        	user.setGender(resultSet.getString("gender"));
        	user.setTelephone(resultSet.getString("telephone"));
            list.add(user);
        }
        return list;
    }
    
    /**
     * 按照用户名查询
     * 模糊查询
     * done.  程淋  0410190215
     * @param username
     * @return
     * @throws SQLException
     */
    public List<User> selectByUserName(String username) throws SQLException {
        List<User> list = new ArrayList<>();
        Connection open = DButils.getConnection();
        String sql = "select * from users where username like ?";
        PreparedStatement ps = open.prepareStatement(sql);
        ps.setString(1,"%"+username+"%");
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()){
        	User users = new User();
        	users.setUsername(resultSet.getString("username"));
        	users.setPassword(resultSet.getString("password"));
        	users.setGender(resultSet.getString("gender"));
        	users.setTelephone(resultSet.getString("telephone"));  
            list.add(users);
        }
        return list;
    }

}
