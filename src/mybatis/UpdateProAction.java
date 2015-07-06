package mybatis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mybatis.CommandAction;
import board.BoardDBBean;
import board.boardInfo;

public class UpdateProAction implements CommandAction {

	public String requestPro(HttpServletRequest request, 
			HttpServletResponse response) throws Throwable{
		
		request.setCharacterEncoding("euc-kr"); 	//�ѱ� ���ڵ�
		String pageNum = request.getParameter("pageNum");
		boardInfo article = new boardInfo();	//������ ó�� ��
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		article.setEmail(request.getParameter("email"));
		article.setSubject(request.getParameter("subject"));
		article.setContent(request.getParameter("content"));
		article.setPasswd(request.getParameter("passwd"));
		MybatisBoardDBBean dbPro = MybatisBoardDBBean.getInstance();	//DB ó��
		int check = dbPro.updateArticle(article);
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		return "qnaboard.do";
	}
}
