package amazon.api;

import amazon.api.controller.BookController;
import junit.framework.TestCase;

public class BookControllerTest extends TestCase {

	private BookController bc;
	
	public void setUp(){
		bc = new BookController();
	}
	
	public void testGetAllBook(){
		
		String expected = "Book [title=Super Livre, author=Marc Dupont, publicationDate=3916-06-10, asin=B00KHTCDC2, publisher=Packt Publishing, pages=220]";
		//String result = this.bc.fetchById("B00KHTCDC2");
		
		//assertEquals(expected, result);	
	}
}
