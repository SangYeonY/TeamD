package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.vo.Order;
import main.OracleConnectUtil;

public class OrderDao {
	
	private static OrderDao orderdao = new OrderDao();
	private OrderDao() {}
	public static OrderDao getOrderDao() {
		return orderdao;
	}
	
	// select 쿼리
	public List<Order> selectAll(){
		Connection conn = OracleConnectUtil.connect();
		String sql = "SELECT * FROM TBL_ORDER";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Order> order = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				order.add(new Order(rs.getInt(1),
							rs.getInt(2),
							rs.getString(3),
							rs.getInt(4),
							rs.getDate(5)));
			}
			pstmt.close();
			
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
		OracleConnectUtil.close(conn);
		return order;
	}
	
	//1개 주문번호 select
		public Order selectOne(int order_num) {
			Connection conn = OracleConnectUtil.connect();
			String sql = "SELECT * FROM TBL_ORDER WHERE ORDER_NUM = ?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Order vo = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, order_num);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					vo = new Order(rs.getInt(1),
							rs.getInt(2),
							rs.getString(3),
							rs.getInt(4),
							rs.getDate(5));
				}
				pstmt.close();
				
			} catch (SQLException e) {
				System.out.println("SQL 실행 오류" + e.getMessage());
			}
			OracleConnectUtil.close(conn);
			return vo;
		}	

	
	// insert 쿼리
	public void insert(Order vo) {
		Connection conn = OracleConnectUtil.connect();
		String sql = "INSERT INTO TEAM_A.TBL_ORDER" +
					"(ORDER_NUM, CUSTOM_NUM, PROD_NAME, QUANTITY, BUY_DATE)" + 
					"VALUES(buy_seq.nextval,?, ?, ?, sysdate)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(vo.getOrder_num(), vo.getCustom_num());
			pstmt.setString(vo.getOrder_num(), vo.getProd_name());
			pstmt.setInt(vo.getOrder_num(), vo.getQuantity());
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류 " + e.getMessage());
		} 
		OracleConnectUtil.close(conn);
	}
	
	//update 쿼리
		public void update(Order vo) {
			Connection conn = OracleConnectUtil.connect();
			String sql = "UPDATE TBL_ORDER SET QUANTITY=? "
					+ "WHERE ORDER_NUM,=?";
			PreparedStatement pstmt = null;
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(vo.getOrder_num(), vo.getQuantity());
				pstmt.setInt(vo.getOrder_num(), vo.getCustom_num());
				pstmt.setString(vo.getOrder_num(), vo.getProd_name());
				
				pstmt.execute();
				pstmt.close();
				
			} catch (SQLException e) {
				System.out.println("SQL 실행 오류" + e.getMessage());
			}
			OracleConnectUtil.close(conn);
		}	
		
		//delete
		public void delete(int order_num) {
			Connection conn = OracleConnectUtil.connect();
			String sql = "DELETE FROM TBL_ORDER WHERE ORDER_NUM=?";
			PreparedStatement pstmt = null;
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, order_num);
				
				pstmt.execute();
				pstmt.close();
				
			} catch (SQLException e) {
				System.out.println("SQL 실행 오류" + e.getMessage());
			}
			OracleConnectUtil.close(conn);
		}		
}
