package amazon.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import amazon.api.dao.BookDAO;

@SpringBootApplication
public class Application 
{
	
    public static void main(String[] args){
    	
    	 SpringApplication.run(Application.class, args);
    	System.out.println(
    			BookDAO.getInstance().fetchOneByField(BookDAO.BOOKFIELD.asin, "B00KHTCDC2")
    			);
    }
}