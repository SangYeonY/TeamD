package db.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class Order {
	private int order_num;
	private int custom_num;
	private String prod_name;
	private int quantity;
	private Timestamp buy_date;
	
	public Order() {
		
	}
	
	
	
	@Override
	public String toString() {
		return "[order_num = " + order_num + ", custom_num = " + custom_num + ", prod_name = " + prod_name
				+ ", quantity = " + quantity + ", buy_date = " + buy_date + "]";
	}
	
	public Order(int custom_num, String prod_name, int quantity) {
		super();
		this.custom_num = custom_num;
		this.prod_name = prod_name;
		this.quantity = quantity;
	}
	

	public Order(int order_num, int custom_num, String prod_name, int quantity, Timestamp buy_date) {
		super();
		this.order_num = order_num;
		this.custom_num = custom_num;
		this.prod_name = prod_name;
		this.quantity = quantity;
		this.buy_date = buy_date;
	}

	public int getOrder_num() {
		return order_num;
	}

	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}

	public int getCustom_num() {
		return custom_num;
	}

	public void setCustom_num(int custom_num) {
		this.custom_num = custom_num;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Timestamp getBuy_date() {
		return buy_date;
	}

	public void setBuy_date(Timestamp buy_date) {
		this.buy_date = buy_date;
	}
	
	
	
	
	
	
}
