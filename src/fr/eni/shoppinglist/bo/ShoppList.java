package fr.eni.shoppinglist.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppList implements Serializable {

	private int id;
	private String name;
	private List<Item> contentList;

	@Override
	public String toString() {
		return "ShoppList [id=" + id + ", name=" + name + ", contentList=" + contentList + "]";
	}

	public ShoppList(String name, List<Item> contentList) {
		super();
		this.name = name;
		this.contentList = contentList;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ShoppList() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item> getContentList() {
		return this.contentList;
	}

	public void setContentList(List<Item> contentList) {
		this.contentList = contentList;
	}

	public int getId() {
		return id;
	}

}
