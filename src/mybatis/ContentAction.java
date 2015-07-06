package mybatis;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mybatis.CommandAction;
import board.boardInfo;

public class ContentAction implements CommandAction {

	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));	//�ش� �� ��ȣ
		System.out.println("num = " + num);
		String pageNum = request.getParameter("pageNum");	//�ش� ������ ��ȣ
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		MybatisBoardDBBean dbPro = MybatisBoardDBBean.getInstance(); 	// DB ó��
		boardInfo article = dbPro.getArticle(num);	// �ش� �� ��ȣ�� ���� �ش� ���ڵ�
		
		//�ش� �信�� ����� �Ӽ�
		System.out.println(num + ":" + pageNum + ":" + article + ":"+ sdf + ":");
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		request.setAttribute("sdf", sdf);
		
		return "/View/board/content.jsp";		// �ش� ��
	}
}