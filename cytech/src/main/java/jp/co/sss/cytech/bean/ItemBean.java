package jp.co.sss.cytech.bean;

import java.io.Serializable;

public class ItemBean implements Serializable{
	private Integer id;
	private String name;
	private Integer price;
	
	public ItemBean(){
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}


