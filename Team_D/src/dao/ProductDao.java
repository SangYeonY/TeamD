package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.vo.Product;
import main.OracleConnectUtil;

public class ProductDao {

	private static ProductDao productdao = new ProductDao();
	private ProductDao() {}
	public static ProductDao getProductDao() {
		return productdao;
	}
	
	// select 쿼리
	
	public List<Product> selectAll(){
		Connection conn = OracleConnectUtil.connect();
		String sql = "SELECT * FROM TBL_PROD";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> product = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				product.add(new Product(rs.getString(1),
							rs.getInt(2)));
			}
			pstmt.close();
			
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
		OracleConnectUtil.close(conn);
		return product;
	}
	
	//1개 행 select 
		public Product selectOne(String prod_name) {
			Connection conn = OracleConnectUtil.connect();
			String sql = "SELECT * FROM TBL_PROD WHERE PROD_NAME = ?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Product vo = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, prod_name);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					vo = new Product(rs.getString(1),
									rs.getInt(2));
				}
				pstmt.close();
				
			} catch (SQLException e) {
				System.out.println("SQL 실행 오류" + e.getMessage());
			}
			OracleConnectUtil.close(conn);
			return vo;
		}	


	
	// insert 쿼리
	public void insert(Product vo) {
		Connection conn = OracleConnectUtil.connect();
		String sql = "INSERT INTO TBL_PROD" +
						"(PROD_NAME,PRICE)" +
						"VALUES (?,?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getProd_name());
			pstmt.setInt(2, vo.getPrice());
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
		OracleConnectUtil.close(conn);
		
	}
	
	//update
		public void update(Product vo) {
			Connection conn = OracleConnectUtil.connect();
			String sql = "UPDATE TBL_PROD SET PRICE=? WHERE PROD_NAME=?";
			PreparedStatement pstmt = null;
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, vo.getPrice());
				pstmt.setString(2, vo.getProd_name());
				
				pstmt.execute();
				pstmt.close();
				
			} catch (SQLException e) {
				System.out.println("SQL 실행 오류" + e.getMessage());
			}
			OracleConnectUtil.close(conn);
		}	
	
		//delete
		public void delete(String prod_name) {
			Connection conn = OracleConnectUtil.connect();
			String sql = "DELETE FROM TBL_PROD WHERE PROD_NAME=?";
			PreparedStatement pstmt = null;
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, prod_name);
				
				pstmt.execute();
				pstmt.close();
				
			} catch (SQLException e) {
				System.out.println("SQL 실행 오류" + e.getMessage());
			}
			OracleConnectUtil.close(conn);
		}	
		
}
