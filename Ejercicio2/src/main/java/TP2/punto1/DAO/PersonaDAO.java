package TP2.punto1.DAO;

import TP2.punto1.entidades.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonaDAO  {
    private EntityManagerFactory emf;

    public PersonaDAO(EntityManagerFactory emf) {
        // Inicializa el EntityManagerFactory con el nombre de la unidad de persistencia
        this.emf = emf;
    }

    // Guardar una nueva persona
    public void save(Persona persona) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(persona);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();

        }
    }

    // Encontrar todas las personas
    public List<Persona> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT p FROM Persona p";
            TypedQuery<Persona> query = em.createQuery(jpql, Persona.class);
            //   Query query = em.createQuery(jpql);
            List<Persona> resultados = query.getResultList();
            for (Persona p : resultados) {
                System.out.println(p);
            }
            return resultados;
        } finally {
            em.close();

        }
    }

    // Encontrar persona por ID
    public Persona findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Persona.class, id);
        } finally {
            em.close();
        }
    }

    // Eliminar persona
    public void delete(Persona persona) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Persona mergedPersona = em.merge(persona);
            em.remove(mergedPersona);
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
