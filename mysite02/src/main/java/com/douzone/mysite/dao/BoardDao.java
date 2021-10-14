package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;

public class BoardDao {
	public List<BoardVo> findAll() {
		List<BoardVo> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = getConnection();
			
			String sql =
						"select b.no, b.title, u.name, b.hit, b.reg_date, b.group_no, b.order_no, b.dept"
						+ " from board b,user u"
						+ " where b.user_no = u.no"
						+ " ORDER BY group_no desc, order_no ASC LIMIT 0,10";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				int hit = rs.getInt(4);
				String regDate = rs.getString(5);
				int groupNo= rs.getInt(6);
				int orderNo = rs.getInt(7);
				int dept = rs.getInt(8);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setName(name);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDept(dept);
				
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	public boolean insert(BoardVo vo) {
	boolean result = false;

	Connection conn = null;
	PreparedStatement pstmt = null;
	try {
		conn = getConnection();
		
		String sql =
				" insert into board"
				+ " values(null, ?,?,?,now(),?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setInt(3, vo.getHit());
		pstmt.setInt(4, vo.getGroupNo());
		pstmt.setInt(5, vo.getOrderNo());
		pstmt.setInt(6, vo.getDept());
		pstmt.setLong(7, vo.getUserNo());
		
		
		int count = pstmt.executeUpdate();
		result = count == 1;
		
	} catch (SQLException e) {
		System.out.println("error:" + e);
	} finally {
		try {
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}		
	
	return result;
}
//	public boolean delete(GuestbookVo vo) {
//		boolean result = false;
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = getConnection();
//			
//			String sql =
//					" delete" +
//					"   from guestbook" +
//					"  where no=?" +
//					"    and password=?";
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setLong(1, vo.getNo());
//			pstmt.setString(2, vo.getPassword());
//			
//			int count = pstmt.executeUpdate();
//			result = count == 1;
//			
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if(pstmt != null) {
//					pstmt.close();
//				}
//				if(conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}		
//		
//		return result;		
//	}
//	

	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		
		return conn;
	}	
}