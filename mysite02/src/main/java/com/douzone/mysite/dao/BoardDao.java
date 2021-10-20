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
	public List<BoardVo> findAll(int pageno) {
		List<BoardVo> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = getConnection();
			
			String sql =
						"select b.no, b.title, u.name, b.hit, b.reg_date, b.group_no, b.order_no, b.dept,u.no"
						+ " from board b,user u"
						+ " where b.user_no = u.no"
						+ " ORDER BY group_no desc, order_no ASC LIMIT ?,10";
			pstmt = conn.prepareStatement(sql);
			int limitNo = (pageno-1)*10;
			pstmt.setLong(1, limitNo);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				int hit = rs.getInt(4);
				String regDate = rs.getString(5);
				Long groupNo= rs.getLong(6);
				int orderNo = rs.getInt(7);
				int dept = rs.getInt(8);
				Long userNo = rs.getLong(9);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setName(name);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDept(dept);
				vo.setUserNo(userNo);

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
	public List<BoardVo> findAll(int pageno,String keyword) {
		List<BoardVo> list = new ArrayList<>();
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
					
			try {
				conn = getConnection();
				
				String sql =
							"select b.no, b.title, u.name, b.hit, b.reg_date, b.group_no, b.order_no, b.dept,u.no"
							+ " from board b,user u"
							+ " where b.user_no = u.no "
							+ " and b.title like ?"
							+ " ORDER BY group_no desc, order_no ASC LIMIT ?,10";
				pstmt = conn.prepareStatement(sql);
		
				pstmt.setString(1, "%"+keyword+"%");
				int limitNo = (pageno-1)*10;
				pstmt.setLong(2, limitNo);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Long no = rs.getLong(1);
					String title = rs.getString(2);
					String name = rs.getString(3);
					int hit = rs.getInt(4);
					String regDate = rs.getString(5);
					Long groupNo= rs.getLong(6);
					int orderNo = rs.getInt(7);
					int dept = rs.getInt(8);
					Long userNo = rs.getLong(9);
					
					BoardVo vo = new BoardVo();
					vo.setNo(no);
					vo.setTitle(title);
					vo.setName(name);
					vo.setHit(hit);
					vo.setRegDate(regDate);
					vo.setGroupNo(groupNo);
					vo.setOrderNo(orderNo);
					vo.setDept(dept);
					vo.setUserNo(userNo);

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
	public BoardVo findByNo(Long no) {

		BoardVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = " select b.title,b.contents, u.no, b.group_no, b.order_no, b.dept"
					+ " from board b,user u"
					+ " where b.no = ?"
					+ "   and b.user_no = u.no";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1,no);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				
				String title = rs.getString(1);
				String content = rs.getString(2);		
				Long userNo = rs.getLong(3);
				Long groupNo = rs.getLong(4);
				int orderNo = rs.getInt(5);
				int depth = rs.getInt(6);
				vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setUserNo(userNo);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDept(depth);
			}	
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;
	}
	public int findTotalPage() {
		int result = 1;
		ResultSet rs = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "  select ceil(a.rnum/10) as page"
						+ "	   from ("
						+ "			select count(*) as rnum "
						+ "			from board"
						+ "		)a";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}finally {
			try {
				if(conn != null) {
					conn.close();	
				}
				if(pstmt != null) {
					pstmt.close();	
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
		return result; 
	}
	public int findTotalPage(String keyword) {
		int result = 1;
		ResultSet rs = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "  select ceil(a.rnum/10) as page"
					+ "	   from ("
					+ "			select count(*) as rnum"
					+ "			from board b,user u"
					+ "            where b.user_no = u.no"
					+ "			and b.title like ?"
					+ "		)a";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}finally {
			try {
				if(conn != null) {
					conn.close();	
				}
				if(pstmt != null) {
					pstmt.close();	
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
		return result; 
	}
	public boolean insert(BoardVo vo) {
	boolean result = false;

	Connection conn = null;
	PreparedStatement pstmt = null;
	try {
		conn = getConnection();
		
		String sql =
				" insert into board "
				+ " select null, ?,?,?,now(), g.no ,?,?,?"
				+ " from(select count(dept)+1 as no"
				+ "		from board b"
				+ "	   where dept =0) g";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setInt(3, vo.getHit());
		pstmt.setInt(4, vo.getOrderNo());
		pstmt.setInt(5, vo.getDept());
		pstmt.setLong(6, vo.getUserNo());
		
		
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
	public boolean commentInsert(BoardVo vo) {
	boolean result = false;

	Connection conn = null;
	PreparedStatement pstmt = null;
	try {
		conn = getConnection();
		
		String sql =
				"  insert into board "
				+ " select null, ?,?,?,now(), ? , ?,?,?";
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setInt(3, vo.getHit());
		pstmt.setLong(4, vo.getGroupNo());
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

	public boolean update(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = 
					"  update board set title = ?, contents =?"
					+" where user_no = ?"
					+" and no = ?";
					
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getUserNo());
			pstmt.setLong(4, vo.getNo());
						
			
			int count = pstmt.executeUpdate();
			result = count == 1;

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
		
		return result;
	}
	public boolean updateOrderNo(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = 
					  " update board set order_no = order_no+1"
					+ " where group_no = ?"
					+ " and order_no >= ?";
					
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getGroupNo());
			pstmt.setInt(2, vo.getOrderNo());
			
			int count = pstmt.executeUpdate();
			result = count == 1;

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
		
		return result;
	}
	public boolean updateHit(Long no) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = 
					     "update board set hit = hit + 1"
					   +" where no = ?";					
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			int count = pstmt.executeUpdate();
			result = count == 1;

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
		
		return result;
	}
	
	public boolean delete(Long no) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql =
					" delete" 
				  + " from board" 
				  +	" where no=?";
					
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);

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