package amazon.api;

import java.net.InetSocketAddress;
import java.sql.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import amazon.api.dao.BookDAO;
import amazon.api.dao.BookDAO.BOOKFIELD;
import amazon.api.pojo.Book;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import junit.framework.TestCase;

public class BookAPITest extends TestCase {
	
	private MongoClient client;
	private MongoServer server;
	
	@Before
    public void setUp() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
       server = new MongoServer(new MemoryBackend());

        // bind on a random local port
        InetSocketAddress serverAddress = server.bind();

       client = new MongoClient(new ServerAddress(serverAddress));
       
       BookDAO.getInstance().MONGO = client;
    }

    @After
    public void tearDown() {
        client.close();
        server.shutdown();
    }

	public void testAllBooks(){
		
		List<Book> expected = BookDAO.getInstance().fecthAll();
		
		List<Book> result = BookDAO.getInstance().fecthAll();
		
		assertEquals(expected.size(), result.size());
		
		for (int i = 0; i < result.size(); i++) {
			assertEquals(expected.get(i), result.get(i));
		}

	}
	
	public void testOneBookByTitle(){
		Book expected = BookDAO.books.get(0);
		
		Book result = BookDAO.getInstance().fetchOneByField(BOOKFIELD.title, expected.getTitle());
		
		assertEquals(expected, result);
		
		expected = null;
		result = BookDAO.getInstance().fetchOneByField(BOOKFIELD.title, "plop");
		assertEquals(expected, result);
	}
	
	public void testAllByTitle(){
		List<Book> expected = BookDAO.books;
		List<Book> result = BookDAO.getInstance().fetchAllByField(BOOKFIELD.title,"Super Livre");
		
		assertEquals(expected.size(), result.size());
		
		for (int i = 0; i < result.size(); i++) {
			assertEquals(expected.get(i), result.get(i));
		}
		
		result = BookDAO.getInstance().fetchAllByField(BOOKFIELD.title, "ajkwndawlnd");
		assertEquals(true, result.isEmpty());
		
		result = BookDAO.getInstance().fetchAllByField(BOOKFIELD.title,null);
		assertEquals(true, result.isEmpty());
	}
	
	public void testGetById(){
		Book expected = BookDAO.books.get(0);
		Book result = BookDAO.getInstance().fetchOneByField(BOOKFIELD.asin, "B00KHTCDC2");
		
		assertEquals(expected, result);
		assertEquals(null,  BookDAO.getInstance().fetchOneByField(BOOKFIELD.asin, "adawdawd"));
	}
	
	public void testAddBook(){
		BookDAO.getInstance().addBook("Super Livre", "marcel", 
			new Date(2016, 10, 10), "BOK3", "Pack", 25);
			
		Book expected = new Book("Super Livre", "marcel", 
			new Date(2016, 10, 10), "BOK3", "Pack", 25);
			
		assertEquals(expected, BookDAO.getInstance().fetchOneByField(BOOKFIELD.asin, "BOK3"));
		
	}
	
	public void testDeleteBook(){
		
		Boolean ok = BookDAO.getInstance().deleteByField(BOOKFIELD.asin, "BOK3");
		Book b = BookDAO.getInstance().fetchOneByField(BOOKFIELD.asin, "BOK3");
		
		assertNull(b);
	}
	
	public void testBuyProcess(){
		
		BookDAO.getInstance().addBook("Super Livre", "marcel", 
				new Date(2016, 10, 10), "BOK353435", "Pack", 25, 30);
		
		
		Book b = BookDAO.getInstance().fetchOneByField(BOOKFIELD.asin, "BOK353435");
		
		assertEquals(30, b.getQuantity());
		
		Boolean result = BookDAO.getInstance().buyBook("BOK353435");
		
		assertTrue(result);
		
		b = BookDAO.getInstance().fetchOneByField(BOOKFIELD.asin, "BOK353435");
		
		assertEquals(29, b.getQuantity());
		
		result = BookDAO.getInstance().buyBook("B00KHTCDC4");
		
	}

}
