package amazon.api.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import amazon.api.pojo.Book;

public class BookDAO {
	
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

	public static List<Book> fetchAll() {
		
		return BookDAO.books;
	}

	public static Book fetchOneByTitle(String title) {
		
		for(Book b : BookDAO.books){
			if(b.getTitle() == title){
				return b;
			}
		}
		
		return null;
	}

	public static List<Book> fetchAllByTitle(String title) {
		
		List<Book> result = new ArrayList<Book>();
		for(Book b : BookDAO.books){
			if(b.getTitle() == title){
				result.add(b);
			}
		}
		
		return result;
	}

	public static Book fetchById(String id) {
		for(Book b : BookDAO.books){
			if(b.getAsin().compareTo(id) == 0){
				return b;
			}
		}
		
		return null;
	}

}
