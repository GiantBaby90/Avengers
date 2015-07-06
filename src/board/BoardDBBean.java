package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDBBean {
	private static BoardDBBean instance = new BoardDBBean();
	// 한번만 생성되고 모든 사용자에게 공유된다.
	public static BoardDBBean getInstance() {
		return instance;
	}
	private BoardDBBean() { }
	public Connection getConnection() throws Exception {
		// 디비 연결 객체 리턴
		Connection conn = null;
		
		try {
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
			String dbId = "scott";
			String dbPass = "tiger";
				Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
			System.out.println("oracle connect");
		} catch (Exception e) {
			System.out.println("Oracle error");
			e.printStackTrace();
		}
		return conn;
	}
	
	public void insertArticle(boardInfo article, String id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int number = 0;
		String query = "";
		System.out.println(id);
		try {
			conn = getConnection(); 
			pstmt = conn.prepareStatement("SELECT Boardser.nextVal from dual");
			rs = pstmt.executeQuery();
			if(rs.next())
				number = rs.getInt(1); 
			else
				number = 1;
			int ref = 1;
			int re_step = 0;
			int re_level = 0;
			
			// 새 글(article.getNum() == 0)이 아닐 경우
			if(article.getNum() > 0) {
				
				// 현재 상태 유지
				ref = article.getRef(); 
				re_step = article.getRe_step();
				re_level = article.getRe_level();
				
				// 쿼리 실행하여 한단계 증가된 상태로 업데이트
				query = "update htmlboard set re_step = re_step + 1 where ref = ? and re_step > ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1,  ref);
				pstmt.setInt(2,  re_step);
				pstmt.executeUpdate();
				re_step += 1; // 상속(re_step, re_level)이 한단계 밀림 
				re_level += 1;
			} else { ref = number;}
			// 새 글인경우 초기값으로 설정
			
			query = "insert into htmlboard (num, writer, email, subject, passwd, reg_date,";
			query += "ref, re_step, re_level, content, ip) values(BoardSer.currVal, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2,  article.getEmail());
			pstmt.setString(3,  article.getSubject());
			pstmt.setString(4,  article.getPasswd());
			pstmt.setTimestamp(5,  article.getReg_date());
			pstmt.setInt(6,  ref);
			pstmt.setInt(7,  re_step);
			pstmt.setInt(8,  re_level);
			pstmt.setString(9,  article.getContent());
			pstmt.setString(10,  article.getIp());
			pstmt.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if(rs != null) try {rs.close(); } catch(SQLException ex) { }
			if(pstmt != null) try {rs.close(); } catch(SQLException ex) { }
			if(conn != null) try {rs.close(); } catch(SQLException ex) { }
			}
		}
	
	public int getArticleCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from htmlboard");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}
	
	public void finallyMethod(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs != null) try {rs.close(); } catch(SQLException ex) { }
		if(pstmt != null) try {rs.close(); } catch(SQLException ex) { }
		if(conn != null) try {rs.close(); } catch(SQLException ex) { }
	
	}
	
	public List getArticles(int start, int end) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List articleList = null;
		String query = "";
		
		try {
			conn = getConnection();
			query = "select * from "
			+ "(select rownum rnum, a.* "
			+ "from (select num, writer, email, subject, passwd, reg_date, readcount, "
			+ "ref, re_step, re_level, content, ip from htmlboard order by ref desc, re_step) a ) "
			+ " where rnum between ? and ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,  start); 
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				articleList = new ArrayList(end);
				do {
					boardInfo article = new boardInfo();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));
					article.setReadcount(rs.getInt("readcount"));
					article.setReg_date(rs.getTimestamp("reg_date"));
					article.setRef(rs.getInt("ref"));
					article.setRe_step(rs.getInt("re_step"));
					article.setRe_level(rs.getInt("re_level"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					articleList.add(article);
				}while(rs.next());
			}
			}catch (Exception ex) { ex.printStackTrace(); } finally {
				finallyMethod(rs, pstmt, conn); }
			return articleList;
		}
	
	public boardInfo getArticle(int num) throws Exception {
		boardInfo article = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update htmlboard set readcount = readcount + 1 where num = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("select * from htmlboard where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { article = new boardInfo();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPasswd(rs.getString("passwd"));
				article.setReadcount(rs.getInt("readcount"));
				article.setReg_date(rs.getTimestamp("reg_date"));
				article.setRef(rs.getInt("ref"));
				article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
			}
			
		} catch (Exception ex){ ex.printStackTrace(); } finally { finallyMethod(rs, pstmt, conn);
	} 
	return article;
	}
	
	public boardInfo updateGetArticle(int num) throws Exception {
		boardInfo article = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select * from htmlboard where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article = new boardInfo();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPasswd(rs.getString("passwd"));
				article.setReadcount(rs.getInt("readcount"));
				article.setReg_date(rs.getTimestamp("reg_date"));
				article.setRef(rs.getInt("ref"));
				article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
			}
		} catch (Exception ex){ ex.printStackTrace(); } finally { finallyMethod(rs, pstmt, conn);
		
	}
		return article;
	}
	
	public int updateArticle(boardInfo article) throws Exception {
		String dbpasswd = "";
		String query = "";
		int x = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select passwd from htmlboard where num = ?");
			pstmt.setInt(1,  article.getNum());
			rs = pstmt.executeQuery();
			System.out.println("select 완료");
			if(rs.next()) { dbpasswd = rs.getString("passwd"); }
			if(dbpasswd.equals(article.getPasswd()))
				query = "update htmlboard set email = ?, subject = ?, passwd = ?, content = ? where num = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1,  article.getEmail());
				pstmt.setString(2,  article.getSubject());
				pstmt.setString(3,  article.getPasswd());
				pstmt.setString(4,  article.getContent());
				pstmt.setInt(5,  article.getNum());
				pstmt.executeUpdate();
				x = 1;
				
		}catch (Exception ex){ ex.printStackTrace(); } finally { finallyMethod(rs, pstmt, conn);
	}
		return x;
	}
	
	public int deleteArticle(int num, String passwd) throws Exception {
		String dbpasswd = "";
		String query = "";
		int x = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select passwd from htmlboard where num = ?");
			pstmt.setInt(1,  num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { dbpasswd = rs.getString("passwd"); }
			if(dbpasswd.equals(passwd))
				pstmt = conn.prepareStatement("delete from htmlboard where num = ?");
				pstmt.setInt(1,  num);
				pstmt.executeUpdate();
				x = 1;
				
		}catch (Exception ex){ ex.printStackTrace(); } finally { finallyMethod(rs, pstmt, conn);
	}
		return x;
	}
}

