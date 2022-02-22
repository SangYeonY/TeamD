package main;

import java.util.List;

import dao.CustomDao;
import dao.OrderDao;
import dao.ProductDao;
import db.vo.Custom;
import db.vo.Order;
import db.vo.Product;

public class TestMain {

	public static void main(String[] args) {
		CustomDao customdao = CustomDao.getCustomDao();
		
		List<Custom> list = customdao.selectAll();
		
		for (Custom vo : list) {
			System.out.println(vo);
		}
		
		OrderDao orderdao = OrderDao.getOrderDao();
		
		List<Order> olist = orderdao.selectAll();
		
		for (Order vo : olist) {
			System.out.println(vo);
		}
		
		ProductDao productdao = ProductDao.getProductDao();
		
		Product productOne = productdao.selectOne("아메리카노");
		System.out.println(productOne);
		
		
		
	}

}
