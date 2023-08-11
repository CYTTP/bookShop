package com.cn;
/**
 *  ≥Ã¡‹  0410190215
 * 
 * @param 
 * @return
 * @throws 
 */
public class Products {
       public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public Number getPrice() {
		return price;
	}
	public void setPrice(Number price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private  int id;
       private String name;
       private String category;
       private Number pnum;
       private Number price;
       private String description;
	public Number getPnum() {
		return pnum;
	}
	public void setPnum(Number pnum) {
		this.pnum = pnum;
	}
}
