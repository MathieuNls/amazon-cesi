package amazon.api.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import amazon.api.pojo.Book;

public class BookDAO extends AmazonDAO<Book> {
	
	public static List<Book> books 
	= new ArrayList<Book>(){{
		add(new Book(
					"Super Livre", 
					"Marc Dupont", 
					new Date(2016, 5, 10),
					"B00KHTCDC2",
					"Packt Publishing",
					220
					));
		add(new Book(
				"Super Livre", 
				"Marc Dupont", 
				new Date(2016, 5, 10),
				"B00KHTCDC3",
				"Packt Publishing",
				220
				));
		add(new Book(
				"Super Livre", 
				"Marc Dupont", 
				new Date(2016, 5, 10),
				"B00KHTCDC4",
				"Packt Publishing",
				220
				));
		add(new Book(
				"Super Livre", 
				"Marc Dupont", 
				new Date(2016, 5, 10),
				"B00KHTCDC5",
				"Packt Publishing",
				220
				));
	}};
	
	public enum BOOKFIELD{
		title, author, publicationDate, asin, publisher, pages;
	}

	
	public static BookDAO getInstance(){
		if(instance == null){
			instance = new BookDAO();
		}
		return (BookDAO) instance;
	}
	
	private BookDAO(){
		this.data = books;
	}
	
	public Book fetchOneByField(BOOKFIELD field, Object value){
		
		try {
			return super.fetchOneByField(field.toString(), value);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Book> fetchAllByField(BOOKFIELD field, Object value){
		
		try {
			return super.fetchAllByField(field.toString(), value);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
