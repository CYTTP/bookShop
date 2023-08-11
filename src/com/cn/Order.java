package com.cn;
/**
 *  ³ÌÁÜ  0410190215
 * 
 * @param 
 * @return
 * @throws 
 */
import java.sql.Time;

public class Order {
 private int user_id;
 private int money;
 private String pay_state;
 private int guest_phone;
 private Time order_time;
 private String guest_name;
 private String guest_address;
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public int getMoney() {
	return money;
}
public void setMoney(int money) {
	this.money = money;
}

public String getPay_state() {
	return pay_state;
}
public void setPay_state(String pay_state) {
	this.pay_state = pay_state;
}
public int getGuest_phone() {
	return guest_phone;
}
public void setGuest_phone(int guest_phone) {
	this.guest_phone = guest_phone;
}

public Time getOrder_time() {
	return order_time;
}
public void setOrder_time(Time order_time) {
	this.order_time = order_time;
}
public String getGuest_name() {
	return guest_name;
}
public void setGuest_name(String guest_name) {
	this.guest_name = guest_name;
}
public String getGuest_address() {
	return guest_address;
}
public void setGuest_address(String guest_address) {
	this.guest_address = guest_address;
}
}
