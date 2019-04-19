package fr.eni.shoppinglist.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.shoppinglist.bo.Item;
import fr.eni.shoppinglist.bo.ShoppList;

public interface ShoppingDAO {

	public List<ShoppList> selectAllList() throws SQLException;

	public List<Item> selectItemById(int id_list) throws SQLException;

	public void deleteListById(int id) throws SQLException;

	public int insert(ShoppList shoppingList) throws SQLException;

	public void insertItem(Item item) throws SQLException;
}
