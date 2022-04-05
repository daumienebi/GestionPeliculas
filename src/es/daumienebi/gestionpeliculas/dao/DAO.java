package es.daumienebi.gestionpeliculas.dao;

import java.util.List;

/**
 * 
 * @author Usuario
 *
 * @param <T> The DAO objecto to be implemented
 * @param <K> The primary key value type
 */
public interface DAO<T,K> {
	void Insert(T x);
	
	void Delete(T x);
	
	List<T> getAll();
	
	void Modify(T x);
	
	/**
	 * 
	 * @param id The id of the object to be searched for
	 * @return An object of <T>
	 */
	T get(K id);
}
