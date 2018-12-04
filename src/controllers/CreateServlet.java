package controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import models.validator.MessageValidator;
import utils.DBUtil;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //セッションID取得
        String _token = request.getParameter("_token");

        //_tokenがnull でなくセッションIDが同一ならば
        if (_token != null && _token.equals(request.getSession().getId())) {

            EntityManager em = DBUtil.createEntityManager();

            Task t = new Task();

            String title = request.getParameter("title");
            t.setTitle(title);

            String content = request.getParameter("content");
            t.setContent(content);

            String closing_date = request.getParameter("closing_date");
            t.setClosing_date(closing_date);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            t.setCreated_at(currentTime);
            t.setUpdated_at(currentTime);

            //バリデーションチェック
            List<String> errors = MessageValidator.validate(t);
            if (errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("task", t);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/task/new.jsp");
                rd.forward(request, response);

            } else {

                em.getTransaction().begin();
                em.persist(t);
                em.getTransaction().commit();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                em.close();

                response.sendRedirect(request.getContextPath() + "/index");
            }
        }
    }
}