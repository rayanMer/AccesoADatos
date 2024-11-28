import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PruebaEclipseLink {

	  public static void main(String[] args) {
	        Pokemon bulbasur = new Pokemon("Bulbasur", "planta");
	        EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("UnidadPersonas");
	        EntityManager em = emf.createEntityManager();
	        try {
	            em.getTransaction().begin();
	            em.persist(bulbasur);
	            em.getTransaction().commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }


	  }}
