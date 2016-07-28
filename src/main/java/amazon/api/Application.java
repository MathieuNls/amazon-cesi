package amazon.api;

import java.sql.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import amazon.api.dao.BookDAO;

@SpringBootApplication
public class Application {

	/**
	 * RETOUR 16H AVEC VOTRE FORK de https://github.com/MathieuNls/amazon-cesi
	 * AVEC DEPLOY AUTO SUR HEROKU
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
		System.out.println(BookDAO.getInstance().fetchOneByField(BookDAO.BOOKFIELD.asin, "B00KHTCDC2"));
		
		BookDAO.getInstance().addBook("Un Livre", "Me", new Date(0), "asin", "paclt", 201);
	}
}