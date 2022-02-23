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
	
	//1개 행 select 
		public Custom selectOne(int custom_num) {
			Connection conn = OracleConnectUtil.connect();
			String sql = "SELECT * FROM TBL_CUSTOM WHERE CUSTOM_NUM = ?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Custom vo = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, custom_num);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					vo = new Custom(rs.getInt(1)
									,rs.getString(2));
				}
				pstmt.close();
				
			} catch (SQLException e) {
				System.out.println("SQL 실행 오류" + e.getMessage());
			}
			OracleConnectUtil.close(conn);
			return vo;
		}
	
	// insert 쿼리
	public void insert(Custom vo) {
		Connection conn = OracleConnectUtil.connect();
		String sql = "INSERT INTO TBL_CUSTOM" +
						"(custom_num,p_or_s)" +
						"VALUES (?,?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getCustom_num());
			pstmt.setString(2, vo.getP_or_s());
			
			pstmt.execute();
			pstmt.close();

		} catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
		OracleConnectUtil.close(conn);
	}
	
	//update
		public void update(Custom vo) {
			Connection conn = OracleConnectUtil.connect();
			String sql = "UPDATE TBL_CUSTOM SET P_OR_S=? WHERE CUSTOM_NUM=?";
			PreparedStatement pstmt = null;
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getP_or_s());
				pstmt.setInt(2, vo.getCustom_num());
				
				pstmt.execute();
				pstmt.close();
				
			} catch (SQLException e) {
				System.out.println("SQL 실행 오류" + e.getMessage());
			}
			OracleConnectUtil.close(conn);
		}
		
		//delete
		public void delete(int custom_num) {
			Connection conn = OracleConnectUtil.connect();
			String sql = "DELETE FROM TBL_CUSTOM WHERE CUSTOM_NUM=?";
			PreparedStatement pstmt = null;
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, custom_num);
				
				pstmt.execute();
				pstmt.close();
				
			} catch (SQLException e) {
				System.out.println("SQL 실행 오류" + e.getMessage());
			}
			OracleConnectUtil.close(conn);
		}
		
		public int selectNum() {
			Connection conn = OracleConnectUtil.connect();
			String sql = "SELECT MAX(CUSTOM_NUM) FROM TBL_CUSTOM";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int max=0;
			try {
				pstmt=conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					max=rs.getInt(1);
				}
				pstmt.close();
				
			} catch (SQLException e) {
				System.out.println("SQL 실행 오류"+e.getMessage());
			}
			OracleConnectUtil.close(conn);
			return max;
		}
		
}
