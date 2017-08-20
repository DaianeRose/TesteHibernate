package Persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Negocio.Item;
import Negocio.Pedido;

import java.util.List;

public class ItemDAO {
	    
	    EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernate");
	    EntityManager entityManager = factory.createEntityManager();
	    
	    public ItemDAO() {
	    	factory = Persistence.createEntityManagerFactory("crudHibernate");
		    entityManager = factory.createEntityManager();
	    
	    }
	    
	
	
	    public void salvar(Item item) {
	        try {
	                 entityManager.getTransaction().begin();
	                 entityManager.merge(item);
	                 entityManager.getTransaction().commit();
	                 entityManager.close();
	        } catch (Exception ex) {
	                 ex.printStackTrace();
	                 entityManager.getTransaction().rollback();
	        }
	   }
	    public void Remover(Item item) {
	    	try {
                entityManager.getTransaction().begin();
                entityManager.remove(item);
                entityManager.getTransaction().commit();
                entityManager.close();
		       } catch (Exception ex) {
		                ex.printStackTrace();
		                entityManager.getTransaction().rollback();
		       }
	    }
	    
	    @SuppressWarnings("unchecked")
	    public List<Item> ConsultarTodos() {
	    	List<Item> item = null;
	    	try {
                entityManager.getTransaction().begin();
                Query consulta = entityManager.createQuery("select item from Item item");
				item = consulta.getResultList();
                entityManager.getTransaction().commit();
                entityManager.close();
		       } catch (Exception ex) {
		                ex.printStackTrace();
		                entityManager.getTransaction().rollback();
		       }
	    	
	    	return item;
	    }
	    
	    public Item ConsultarPeloId(int id) {
	    	Item item = null;
	    	try {
                entityManager.getTransaction().begin();
                Query consulta = entityManager.createQuery("select item from Item item where item.id = :id");
                consulta.setParameter("id", id);
				item = (Item) consulta.getSingleResult();
                entityManager.getTransaction().commit();
                entityManager.close();
		       } catch (Exception ex) {
		                ex.printStackTrace();
		                entityManager.getTransaction().rollback();
		       }
	    	
	    	return item;
	    }
	    
	    
}