package fr.eni.shoppinglist.dal;

public abstract class DAOFactory {

	public static ShoppingDAO getShoppingDAO() {
		return new ShoppinListDAOJdbcImpl();
	}
}
