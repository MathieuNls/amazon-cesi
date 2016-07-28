package amazon.api.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import amazon.api.pojo.IModel;

/**
 * Generic DAO
 * @author math
 *
 * @param <T extends IModel>
 */
public abstract class AmazonDAO<T extends IModel> {
	
	private static MongoClient MONGO = new MongoClient(
				new MongoClientURI("mongodb://amazon-api:amazon-api@ds029665.mlab.com:29665/heroku_z3h74d0s")
			);
	
	private static MongoDatabase db = MONGO.getDatabase("heroku_z3h74d0s");
	
	private String collection;
	
	protected List<T> data;
	/**
	 * So child can be Singletons
	 */
	protected static AmazonDAO<? extends IModel> instance = null;
	
	protected AmazonDAO(String collection){
		this.collection = collection;
	}
	
	/**
	 * Fetch One By Field
	 * @param field
	 * @param value
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	protected T fetchOneByField(String field, Object value) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		for(T item : data){
			
			Field innerField = item.getClass().getDeclaredField(field);
			innerField.setAccessible(true);
			
			if(innerField.get(item).equals(value)){
				return item;
			}
		}
		
		return null;
	}
	
	/**
	 * Fetch All By Field
	 * @param field
	 * @param value
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	protected List<T> fetchAllByField(String field, Object value) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		
		List<T> result = new ArrayList<T>();
		for(T item : data){
			Field innerField = item.getClass().getDeclaredField(field);
			innerField.setAccessible(true);
			
			if(innerField.get(item).equals(value)){
				result.add(item);
			}
		}
		
		return result;
	}
	
	protected void addItem(T item) throws IllegalArgumentException, IllegalAccessException{
		
		Field[] fields = item.getClass().getDeclaredFields();
		
		Document doc = new Document();
		
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			doc.append(fields[i].getName(), fields[i].get(item));
		}
		
		System.out.println(db.getClass().getSimpleName());
		
		db.getCollection(this.collection).insertOne(doc);
		
	}
	
	/**
	 * Fetch All
	 * @return
	 */
	public List<T> fecthAll(){
		return data;
	}

	public boolean deleteByField(String field, Object value) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		
		int sizeBefore = data.size();
		
		for (int i = 0; i < data.size(); i++) {
			Field innerField = data.get(i).getClass().getDeclaredField(field);
			innerField.setAccessible(true);
			
			if(innerField.get(data.get(i)).equals(value)){
				data.remove(i);
			}
		}
		
		return sizeBefore != data.size();
	}
	


}
