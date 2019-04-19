package fr.eni.shoppinglist.dal;

import java.util.ArrayList;
import java.util.List;

import fr.eni.shoppinglist.bo.Item;
import fr.eni.shoppinglist.bo.ShoppList;
import fr.eni.shoppinglist.dal.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoppinListDAOJdbcImpl implements ShoppingDAO {

	private final String SELECT_ALL = "SELECT id, nom  FROM LISTES";
	private final String SELECT_ITEM_BY_ID = "SELECT id, nom, id_liste FROM ARTICLES WHERE id_liste = ?";
	private final String INSERT_LIST = "INSERT INTO LISTES VALUES (?)";
	private final String INSERT_ITEM = "INSERT INTO ARTICLES VALUES (?, ?)";
	private final String DELETE_LIST_BY_ID = "DELETE FROM LISTES WHERE id = ?";

	@Override
	public List<ShoppList> selectAllList() throws SQLException {

		List<ShoppList> shoppingLists = new ArrayList<ShoppList>();
		List<Item> currentItemList = new ArrayList<Item>();
		ShoppList currentList = new ShoppList();

		Connection cx = ConnectionProvider.getConnection();
		PreparedStatement pstmt = cx.prepareStatement(SELECT_ALL);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			if (rs.getInt("id") != currentList.getId()) {
				currentList = SLBuilder(rs);
				currentItemList = new ArrayList<Item>();
				PreparedStatement pstmt2 = cx.prepareStatement(SELECT_ITEM_BY_ID);
				pstmt2.setInt(1, currentList.getId());
				ResultSet rs2 = pstmt2.executeQuery();

				while (rs2.next()) {

					Item item = Ibuilder(rs2);
					currentItemList.add(item);
				}
				currentList.setContentList(currentItemList);
				shoppingLists.add(currentList);
			}
		}

		return shoppingLists;
	}

	@Override
	public List<Item> selectItemById(int id) throws SQLException {
		List<Item> currentItemList = new ArrayList<Item>();

		Connection cx = ConnectionProvider.getConnection();
		PreparedStatement pstmt = cx.prepareStatement(SELECT_ITEM_BY_ID);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			currentItemList.add(Ibuilder(rs));
		}
		return currentItemList;
	}

	@Override
	public int insert(ShoppList shoppingList) throws SQLException {
		int list_id;
		Connection cx = ConnectionProvider.getConnection();
		PreparedStatement pstmt = cx.prepareStatement(INSERT_LIST, PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setString(1, shoppingList.getName());
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		rs.next();
		list_id = rs.getInt(1);

		rs.close();
		pstmt.close();
		return list_id;
	}

	@Override
	public void insertItem(Item item) throws SQLException {
		Connection cx = ConnectionProvider.getConnection();
		PreparedStatement pstmt = cx.prepareStatement(INSERT_ITEM);
		pstmt.setString(1, item.getName());
		pstmt.setInt(2, item.getId_liste());
		ResultSet rs = pstmt.executeQuery();
	}

	@Override
	public void deleteListById(int id) throws SQLException {
		Connection cx = ConnectionProvider.getConnection();
		PreparedStatement pstmt = cx.prepareStatement(DELETE_LIST_BY_ID);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
	}

	// Construit une ShoppingList
	private ShoppList SLBuilder(ResultSet rs) throws SQLException {
		ShoppList shoppList = new ShoppList();
		shoppList.setName(rs.getString("nom"));
		shoppList.setId(rs.getInt(1));
		return shoppList;

	}

	// Construit un Item de la ShoppingList
	private Item Ibuilder(ResultSet rs) throws SQLException {
		Item item = new Item();
		item.setId_liste(rs.getInt("id_liste"));
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("nom"));

		return item;
	}
}
