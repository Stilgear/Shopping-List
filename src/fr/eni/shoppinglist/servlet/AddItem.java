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
import fr.eni.shoppinglist.bo.Item;

/**
 * Servlet implementation class AddItem
 */
@WebServlet(description = "add an item to the selected list", urlPatterns = { "/AddItem" })
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ShoppingListManager ShLMgr = new ShoppingListManager();
		int liste_id = Integer.valueOf(request.getParameter("id_list"));
		String nom = request.getParameter("name");
		try {
			List<Item> itemList = ShLMgr.selectItemById(liste_id);
			request.setAttribute("items", itemList);
			request.setAttribute("nom", nom);
			request.setAttribute("id_list", request.getParameter("id_list"));
			request.getRequestDispatcher("/WEB-INF/additem.jsp").forward(request, response);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ShoppingListManager ShLMgr = new ShoppingListManager();
		Item item = new Item();
		item.setName(request.getParameter("itemName"));
		item.setId_liste(Integer.valueOf(request.getParameter("listId")));
		try {
			ShLMgr.insertItem(item);
			doGet(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
