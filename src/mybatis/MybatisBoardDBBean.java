package mybatis;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import board.boardInfo;

public class MybatisBoardDBBean {
	private final String namespace = "ldg.mybatis";
	private static MybatisBoardDBBean instance = new MybatisBoardDBBean();
	
	public static MybatisBoardDBBean getInstance() {
		return instance;
	}
	
	SqlSession sqlSession;
	
	public List<boardInfo> selectBoard() {
		sqlSession = sqlMapLocator.sqlSession();
		System.out.println("selectboard");
		try {
			return sqlSession.selectList(namespace + ".boardList");
		} finally {
			sqlSession.close();
		}
	}
	
	public int getArticleCount() throws Exception {
		sqlSession = sqlMapLocator.sqlSession();
		System.out.println("getArticleCount===old");
		try {
			return sqlSession.selectOne(namespace + ".getArticleCount");
		} finally {
			sqlSession.close();
		}
	}
	
	public List getArticles(int start, int end) throws Exception {
		sqlSession = sqlMapLocator.sqlSession();
		System.out.println("getArticles===old");
		HashMap map = new HashMap();
		map.put("start",  start);
		map.put("end",  end);
		
		try {
			return sqlSession.selectList(namespace + ".getArticles", map);
		} finally {
			sqlSession.close();
		}
	}
	
	public void insertArticle(boardInfo article) throws Exception {
		sqlSession = sqlMapLocator.sqlSession();
		int num = article.getNum();
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();
		
		try {
			HashMap map = new HashMap();
			System.out.println("namespace = " + namespace);
			int number = sqlSession.selectOne(namespace + ".insertArticle_new");
			if(number != 0)
				number = number + 1;
			else number = 1;
			if(num != 0) {
				map.put("ref",  ref);
				map.put("re_step",  re_step);
				sqlSession.update(namespace + ".insertArticle_update", map);
				sqlSession.commit();
				re_step += 1;
				re_level += 1;
			} else {
				ref = number;
				re_step = 0;
				re_level = 0;
			}
			
			article.setNum(number);
			article.setRef(ref);
			article.setRe_step(re_step);
			article.setRe_level(re_level);
			System.out.println("insert : " + article);
			
			int result = sqlSession.insert(namespace + ".insertArticle_insert", article);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			sqlSession.commit();
			sqlSession.close();
		}
	}
	
	public boardInfo getArticle(int num) throws Exception {
		sqlSession = sqlMapLocator.sqlSession();
		HashMap map = new HashMap();
		map.put("num", num);
		boardInfo article = new boardInfo();
		try {
			int result = sqlSession.update(namespace + ".update_readcount", map);
			article = (boardInfo) sqlSession.selectOne(namespace + ".boardList", map);
		} finally {
			sqlSession.commit();
			sqlSession.close();
			return article;
		}
	}
	
	public boardInfo updateGetArticle(int num) throws Exception {
		sqlSession = sqlMapLocator.sqlSession();
		HashMap map = new HashMap();
		map.put("num", num);
		boardInfo article = new boardInfo();
		
		try {
			article = (boardInfo) sqlSession.selectOne(namespace + ".boardList", map);
			System.out.println(":::" + article);
		} finally {
			sqlMapLocator.sqlSession().close();
			return article;
		}
	}
	
	public int updateArticle(boardInfo article) throws Exception {
		sqlSession = sqlMapLocator.sqlSession();
		HashMap map = new HashMap();
		map.put("num", article.getNum());
		
		int x = -1;
		try {
			String dbpasswd = (String) sqlSession.selectOne(namespace + ".update_passwd", map);
			if(dbpasswd.equals(article.getPasswd())) {
				x = sqlSession.update(namespace + ".update_update", article);
			} 
		} finally {
			sqlSession.commit(); sqlSession.close();
		} return x;
	}
	
	public int deleteArticle(int num, String passwd) throws Exception {
		sqlSession = sqlMapLocator.sqlSession();
		HashMap map = new HashMap();
		map.put("num", num);
		int x = -1;
		
		try {
			String dbpasswd = (String) sqlSession.selectOne(namespace + ".update_passwd", map);
			if(dbpasswd.equals(passwd)) {
				x = sqlSession.delete(namespace + ".delete", map);
			}
		} finally { sqlSession.commit(); sqlSession.close(); }
		return x;
	}
}
