package amazon.api;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import amazon.api.dao.BookDAO;
import amazon.api.pojo.Book;
import junit.framework.TestCase;

public class BookAPI extends TestCase {


	public void testAllBooks(){
		
		List<Book> expected = BookDAO.books;
		
		List<Book> result = BookDAO.fetchAll();
		
		assertEquals(expected.size(), result.size());
		
		for (int i = 0; i < result.size(); i++) {
			assertEquals(expected.get(i), result.get(i));
		}

	}
	
	public void testOneBookByTitle(){
		Book expected = BookDAO.books.get(0);
		
		Book result = BookDAO.fetchOneByTitle(expected.getTitle());
		
		assertEquals(expected, result);
		
		expected = null;
		result = BookDAO.fetchOneByTitle("plop");
		assertEquals(expected, result);
	}
	
	public void testAllByTitle(){
		List<Book> expected = BookDAO.books;
		List<Book> result = BookDAO.fetchAllByTitle("Super Livre");
		
		assertEquals(expected.size(), result.size());
		
		for (int i = 0; i < result.size(); i++) {
			assertEquals(expected.get(i), result.get(i));
		}
		
		result = BookDAO.fetchAllByTitle("ajkwndawlnd");
		assertEquals(true, result.isEmpty());
		
		result = BookDAO.fetchAllByTitle(null);
		assertEquals(true, result.isEmpty());
	}
	
	public void testGetById(){
		Book expected = BookDAO.books.get(0);
		Book result = BookDAO.fetchById("B00KHTCDC2");
		
		assertEquals(expected, result);
		assertEquals(null,  BookDAO.fetchById("adawdawd"));
	}

}
