package TP2.punto1.DAO;
import TP2.punto1.entidades.Socio;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class SocioDAO {
    private EntityManagerFactory emf;

    public SocioDAO(EntityManagerFactory emf) {
        // Inicializa el EntityManagerFactory con el nombre de la unidad de persistencia
        this.emf = emf;
    }

    // Guardar una nueva persona
    public void save(Socio socio) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(socio);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();

        }
    }

    // Encontrar todas las personas
    public List<Socio> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT s FROM Socio s";
            TypedQuery<Socio> query = em.createQuery(jpql, Socio.class);
            //   Query query = em.createQuery(jpql);
            List<Socio> resultados = query.getResultList();
            for (Socio s : resultados) {
                System.out.println(s);
            }
            return resultados;
        } finally {
            em.close();

        }
    }

    // Encontrar persona por ID
    public Socio findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Socio.class, id);
        } finally {
            em.close();
        }
    }

    // Eliminar persona
    public void delete(Socio socio) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Socio mergeSocio = em.merge(socio);
            em.remove(mergeSocio);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Cerrar el EntityManagerFactory al finalizar
    public void close() {
        if (emf != null) {
            emf.close();
        }
    }
}