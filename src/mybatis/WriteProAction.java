package mybatis;

import java.io.File;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mybatis.CommandAction;
import board.boardInfo;

public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("euc-kr");
		boardInfo article = new boardInfo();
		
		
		// 파일 업로드 ////////////////////////
		String saveFolder = "/fileSave";
		String realFolder = "";
		String encType = "euc-kr"; 
		int maxSize = 5 * 1024 * 1024; // 맥스 파일 사이즈
		ServletContext context = request.getServletContext();
		// 현재 JSP 페이지의 웹 어플리케이션상의 절대 경로를 구한다.
		realFolder = context.getRealPath(saveFolder);
		System.out.println(realFolder);
		MultipartRequest multi = 
			new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		
		///////////////////////////////////
		article.setNum(Integer.parseInt(multi.getParameter("num")));
		article.setWriter(multi.getParameter("writer"));
		article.setEmail(multi.getParameter("email"));
		article.setSubject(multi.getParameter("subject"));
		article.setPasswd(multi.getParameter("passwd"));
		article.setReg_date(new Timestamp(System.currentTimeMillis()));
		article.setRef(Integer.parseInt(multi.getParameter("ref")));
		article.setRe_step(Integer.parseInt(multi.getParameter("re_step")));
		article.setRe_level(Integer.parseInt(multi.getParameter("re_level")));
		article.setContent(multi.getParameter("content"));
		article.setIp(request.getRemoteAddr());

		try {
			String filename = multi.getFilesystemName("uploadFile");
			File file = multi.getFile("uploadFile");
			article.setFilename(filename);
			int fsize = (int)file.length();
			article.setFilesize(fsize);
			System.out.println(article);
			MybatisBoardDBBean dbPro = MybatisBoardDBBean.getInstance();
			dbPro.insertArticle(article);
			
			} catch (Exception ex) { System.out.println(ex); }
		return "qnaboard.do";
	}

}
