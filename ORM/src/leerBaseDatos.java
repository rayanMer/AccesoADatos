import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class leerBaseDatos {

	public static void main(String[] args) {
		Autor autor = new Autor(3, "Julio", "Cortázar", "Argentina", "1914-08-26");

		Libro lib1 = new Libro(2, "Rayuela", "Ficción", 1963, autor);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("libros");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(lib1);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

}
