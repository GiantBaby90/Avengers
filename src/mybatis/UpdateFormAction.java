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
		System.out.println(num); // ��
		String pageNum = request.getParameter("pageNum");
		
		MybatisBoardDBBean dbPro = MybatisBoardDBBean.getInstance();
		boardInfo article = dbPro.updateGetArticle(num);
		
		//�ش� �信�� ����� �Ӽ�
		request.setAttribute("num", num);
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		
		return "/View/board/updateForm.jsp";	//�ش� ��
	}

}
