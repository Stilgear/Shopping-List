package fr.eni.shoppinglist.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.shoppinglist.bo.Item;
import fr.eni.shoppinglist.bo.ShoppList;
import fr.eni.shoppinglist.dal.DAOFactory;
import fr.eni.shoppinglist.dal.ShoppingDAO;

public class ShoppingListManager {

	private ShoppingDAO shoppingDAO;

	public ShoppingListManager() {
		this.shoppingDAO = DAOFactory.getShoppingDAO();
	}

	public List<ShoppList> selectAll() throws SQLException {
		return this.shoppingDAO.selectAllList();
	}

	public List<Item> selectItemById(int id) throws SQLException {
		return this.shoppingDAO.selectItemById(id);
	}

	public int insert(ShoppList shoppingList) throws SQLException {
		return this.shoppingDAO.insert(shoppingList);
	}

	public void insertItem(Item item) throws SQLException {
		this.shoppingDAO.insertItem(item);
	}

	public void deleteListById(int id) throws SQLException {
		this.shoppingDAO.deleteListById(id);
	}
}
