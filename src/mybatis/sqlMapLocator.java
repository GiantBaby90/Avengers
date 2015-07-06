package mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class sqlMapLocator {
	static SqlSession sqlSession;
	
	public static SqlSession sqlSession() {
		String resource = "mybatis/SqlMapConfig.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		return new SqlSessionFactoryBuilder().build(inputStream).openSession();
	}
	
	public static void main (String [] args) {
		String namespace = "ldg.mybatis";
		sqlSession = sqlMapLocator.sqlSession();
		System.out.println("selectboard");
		try {
			System.out.println(sqlSession().selectList(namespace + ".boardList"));
		} finally { sqlSession.close(); }
	}
}
