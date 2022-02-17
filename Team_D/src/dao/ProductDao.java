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
	
	
	
}
