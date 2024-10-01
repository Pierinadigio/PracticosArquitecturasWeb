package TP2.punto1.DAO;

import TP2.punto1.entidades.Direccion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class DireccionDAO  {
    private EntityManagerFactory emf;

    public DireccionDAO(EntityManagerFactory emf) {
        // Inicializa el EntityManagerFactory con el nombre de la unidad de persistencia
        this.emf = emf;
    }

    // Guardar una nueva persona
    public void save(Direccion direccion) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(direccion);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();

        }
    }

    // Encontrar todas las personas
    public List<Direccion> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT d FROM Direccion d";
            TypedQuery<Direccion> query = em.createQuery(jpql, Direccion.class);
            //   Query query = em.createQuery(jpql);
            List<Direccion> resultados = query.getResultList();
            for (Direccion d : resultados) {
                System.out.println(d);
            }
            return resultados;
        } finally {
            em.close();

        }
    }

    // Encontrar direccion por ID
    public Direccion findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Direccion.class, id);
        } finally {
            em.close();
        }
    }

    // Eliminar direccion
    public void delete(Direccion direccion) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Direccion mergedDireccion = em.merge(direccion);
            em.remove(mergedDireccion);
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

