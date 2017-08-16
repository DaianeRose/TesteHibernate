
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PedidoDao {
	
	    
	    EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernate");
	    EntityManager entityManager = factory.createEntityManager();
	
	
	    public void salvar(Pedido pedido) {
	        try {
	                 entityManager.getTransaction().begin();
	                 entityManager.persist(pedido);
	                 entityManager.getTransaction().commit();
	        } catch (Exception ex) {
	                 ex.printStackTrace();
	                 entityManager.getTransaction().rollback();
	        }
	   }
	    
}