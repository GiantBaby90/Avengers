package mybatis;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mybatis.CommandAction;
import board.boardInfo;

public class ContentAction implements CommandAction {

	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));	//해당 글 번호
		System.out.println("num = " + num);
		String pageNum = request.getParameter("pageNum");	//해당 페이지 번호
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		MybatisBoardDBBean dbPro = MybatisBoardDBBean.getInstance(); 	// DB 처리
		boardInfo article = dbPro.getArticle(num);	// 해당 글 번호에 대한 해당 레코드
		
		//해당 뷰에서 사용할 속성
		System.out.println(num + ":" + pageNum + ":" + article + ":"+ sdf + ":");
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		request.setAttribute("sdf", sdf);
		
		return "/View/board/content.jsp";		// 해당 뷰
	}
}