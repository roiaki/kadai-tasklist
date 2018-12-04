package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //CSRF セキュリティ対策　hidden要素で送られた値とセッションに格納された値が同一であれば送信を受け付ける
        request.setAttribute("_token", request.getSession().getId());

        //リクエストスコープに保存する
        request.setAttribute("task", new Task());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/task/new.jsp");
        rd.forward(request, response);
	}
}
