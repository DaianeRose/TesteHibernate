package Persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Negocio.Pedido;
import java.util.List;

public class PedidoDao {
	
	    
	    EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernate");
	    EntityManager entityManager = factory.createEntityManager();
	    
	    public PedidoDao() {
	    	factory = Persistence.createEntityManagerFactory("crudHibernate");
		    entityManager = factory.createEntityManager();
	    
	    }
	    
	
	
	    public void salvar(Pedido pedido) {
	        try {
	                 entityManager.getTransaction().begin();
	                 entityManager.merge(pedido);
	                 entityManager.getTransaction().commit();
	                 entityManager.close();
	        } catch (Exception ex) {
	                 ex.printStackTrace();
	                 entityManager.getTransaction().rollback();
	        }
	   }
	    public void Remover(Pedido pedido) {
	    	try {
                entityManager.getTransaction().begin();
                entityManager.remove(pedido);
                entityManager.getTransaction().commit();
                entityManager.close();
		       } catch (Exception ex) {
		                ex.printStackTrace();
		                entityManager.getTransaction().rollback();
		       }
	    }
	    
	    @SuppressWarnings("unchecked")
	    public List<Pedido> ConsultarTodos() {
	    	List<Pedido> pedido = null;
	    	try {
                entityManager.getTransaction().begin();
                Query consulta = entityManager.createQuery("select pedido from Pedido pedido");
				pedido = consulta.getResultList();
                entityManager.getTransaction().commit();
                entityManager.close();
		       } catch (Exception ex) {
		                ex.printStackTrace();
		                entityManager.getTransaction().rollback();
		       }
	    	
	    	return pedido;
	    }
	    
	    public Pedido ConsultarPeloId(int id) {
	    	Pedido pedido = null;
	    	try {
                entityManager.getTransaction().begin();
                Query consulta = entityManager.createQuery("select pedido from Pedido pedido where pedido.id = :id");
                consulta.setParameter("id", id);
				pedido = (Pedido) consulta.getSingleResult();
                entityManager.getTransaction().commit();
                entityManager.close();
		       } catch (Exception ex) {
		                ex.printStackTrace();
		                entityManager.getTransaction().rollback();
		       }
	    	
	    	return pedido;
	    }
	    
	    
}