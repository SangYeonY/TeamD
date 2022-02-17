package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.vo.Custom;
import main.OracleConnectUtil;

public class CustomDao {
	
	private static CustomDao customdao = new CustomDao();
	private CustomDao() {}
	public static CustomDao getCustomDao() {
		return customdao;
	}
	
	// select 쿼리
	public List<Custom> selectAll(){
	Connection conn = OracleConnectUtil.connect();
	String sql = "SELECT * FROM TBL_CUSTOM tc";
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	List<Custom> customs = new ArrayList<>();
	
	try {
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			customs.add(new Custom(rs.getInt(1),
					rs.getString(2)));
		}
		pstmt.close();
		
	} catch (SQLException e) {
		System.out.println("SQL 실행 오류 : " + e.getMessage());
	} 
	OracleConnectUtil.close(conn);
	return customs;
		
	}
	
	// insert 쿼리
	public void insert(Custom vo) {
		Connection conn = OracleConnectUtil.connect();
		String sql = "INSERT INTO TBL_CUSTOM" +
						"(custom_num,p_or_s)" +
						"VALUES (cus_seq.nextval,'?')";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(vo.getCustom_num(), vo.getP_or_s());
			
			pstmt.execute();
			pstmt.close();

		} catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
		OracleConnectUtil.close(conn);
	}
	
	
}
