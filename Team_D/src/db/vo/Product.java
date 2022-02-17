package db.vo;

public class Product {
	private String prod_name;
	private int price;
	
	
	
	@Override
	public String toString() {
		return "[prod_name=" + prod_name + ", price=" + price + "]";
	}



	public Product(String prod_name, int price) {
		super();
		this.prod_name = prod_name;
		this.price = price;
	}



	public String getProd_name() {
		return prod_name;
	}



	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
	
	
	
}
