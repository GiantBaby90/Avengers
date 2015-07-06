package mybatis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mybatis.CommandAction;
import board.BoardDBBean;
import board.boardInfo;

public class UpdateFormAction implements CommandAction {

	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println(num); // µÊ
		String pageNum = request.getParameter("pageNum");
		
		MybatisBoardDBBean dbPro = MybatisBoardDBBean.getInstance();
		boardInfo article = dbPro.updateGetArticle(num);
		
		//ÇØ´ç ºä¿¡¼­ »ç¿ëÇÒ ¼Ó¼º
		request.setAttribute("num", num);
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		
		return "/View/board/updateForm.jsp";	//ÇØ´ç ºä
	}

}
