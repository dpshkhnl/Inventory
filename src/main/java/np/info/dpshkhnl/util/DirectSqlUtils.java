package np.info.dpshkhnl.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DirectSqlUtils {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("suchiPU");
	static EntityManager em=emf.createEntityManager();
	public DirectSqlUtils() {
		// TODO Auto-generated constructor stub
	}
	public static Object getSingleValueFromTable(String directSqlQuery)
	{		
		return em.createNativeQuery(directSqlQuery).getSingleResult();
	}
	public static List<?> getValueListFromTable(String directSqlQuery)
	{		
		Query query = em.createNativeQuery(directSqlQuery);
		return query.getResultList();
	}
	
	public static int update(String directSqlQuery)
	{		
		Query query = em.createNativeQuery(directSqlQuery);
		return query.executeUpdate();
	}
	
	/**Added By Sudeep
	 * @param query = Native SQL query; eg: SELECT * FROM table  
	 * @param c = name of class to cast to get desired result; eg: ReceiptModel.class
	 * @return  List.
	 * 
	 *       The returned List depends on the SQL query result and class passed.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getListOfResultSets(String query, Class<T>  c){
		em=emf.createEntityManager();
		Query query1 = em.createNativeQuery(query,c);
		return (List<T>)query1.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getSingleResult(String directSqlQuery) {
		Query query = em.createNativeQuery(directSqlQuery);
	    query.setMaxResults(1);
	    List<?> list = query.getResultList();
	    if (list == null || list.size() == 0) {
	        return null;
	    }
	    return (T) list.get(0);
	}
	
	
	

}