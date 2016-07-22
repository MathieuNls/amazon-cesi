package amazon.api.controller;


import amazon.api.dao.BookDAO;
import amazon.api.pojo.Book;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class BookController {
	
	@RequestMapping("/book")
	public Book fetchById(@RequestParam(value="id", defaultValue="World") String id) {
		System.err.println(id);
		
		Book b = BookDAO.getInstance().fetchOneByField(BookDAO.BOOKFIELD.asin, id);
		
		System.err.println(b);
		//localhost:8080/book?id=B00KHTCDC2
		return b;
	}

}
