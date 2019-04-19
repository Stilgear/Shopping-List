package fr.eni.shoppinglist.bo;

/*COMMENTAIRES*/
public class Item {
	
	private int id;
	private int id_liste;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setId_liste(int id_liste) {
		this.id_liste = id_liste;
	}

	public int getId_liste() {
		return id_liste;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", id_list=" + id_liste + ", name=" + name + "]";
	}

	public Item(int id_liste, String name) {
		super();
		this.id_liste = id_liste;
		this.name = name;
	}

	public Item() {
		// TODO Auto-generated constructor stub
	}

}
