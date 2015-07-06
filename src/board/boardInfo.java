package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import board.BoardDBBean;

public class boardInfo {
	private int num;
	private String writer;
	private String email;
	private String subject;
	private String passwd;
	private Timestamp reg_date;
	private int readcount;
	private int ref;
	private int re_step;
	private int re_level;
	private String content;
	private String ip;
	private int filesize;
	public int getFilesize() {
		return filesize;
	}

	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	private String filename;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getRe_step() {
		return re_step;
	}

	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}

	public int getRe_level() {
		return re_level;
	}

	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public void finallyMethod(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs != null) try {rs.close(); } catch(SQLException ex) { }
		if(pstmt != null) try {rs.close(); } catch(SQLException ex) { }
		if(conn != null) try {rs.close(); } catch(SQLException ex) { }
	
	}
	
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

	@Override
	public String toString() {
		return "boardInfo [num=" + num + ", writer=" + writer + ", email="
				+ email + ", subject=" + subject + ", passwd=" + passwd
				+ ", reg_date=" + reg_date + ", readcount=" + readcount
				+ ", ref=" + ref + ", re_step=" + re_step + ", re_level="
				+ re_level + ", content=" + content + ", ip=" + ip
				+ ", filesize=" + filesize + ", filename=" + filename + "]";
	}

	
	
	
}
