package fr.eni.shoppinglist.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.shoppinglist.bll.ShoppingListManager;
import fr.eni.shoppinglist.bo.ShoppList;

/**
 * Servlet implementation class DisplayList
 */
@WebServlet(description = "display a list of shoppingList", urlPatterns = { "/DisplayList" })
public class DisplayList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisplayList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ShoppingListManager ShLMgr = new ShoppingListManager();
		try {
			List<ShoppList> shoppingList = ShLMgr.selectAll();
			request.setAttribute("listes", shoppingList);
			if (request.getParameter("name") == null) {
				ShLMgr.deleteListById(Integer.valueOf(request.getParameter("id_list")));
				request.getRequestDispatcher("/WEB-INF/shopplist.jsp").forward(request, response);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException z) {
			z.printStackTrace();

		}
		request.getRequestDispatcher("/WEB-INF/shopplist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ShoppingListManager ShLMgr = new ShoppingListManager();
		ShoppList shoppingList = new ShoppList();
		if (request.getParameter("name") == null) {
			shoppingList.setName((String) request.getParameter("listName"));
			try {
				ShLMgr.insert(shoppingList);
				doGet(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
