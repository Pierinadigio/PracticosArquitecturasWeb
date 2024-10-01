package TP2.punto1.DAO;

import TP2.punto1.DTO.PersonaTurnoDTO;
import TP2.punto1.entidades.Persona;
import TP2.punto1.entidades.Turno;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class TurnoDAO  {
    private EntityManagerFactory emf;

    public TurnoDAO(EntityManagerFactory emf) {
        // Inicializa el EntityManagerFactory con el nombre de la unidad de persistencia
        this.emf = emf;
    }

public void save(Turno turno) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (turno.getId() == 0) {
                em.persist(turno);
            } else {
                em.merge(turno);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    // Encontrar todas las Turnos
    public List<Turno> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT t FROM Turno t";
            TypedQuery<Turno> query = em.createQuery(jpql, Turno.class);
            //   Query query = em.createQuery(jpql);
            List<Turno> resultados = query.getResultList();
            for (Turno t : resultados) {
                System.out.println(t);
            }
            return resultados;
        } finally {
            em.close();

        }
    }

    // Encontrar  por ID
    public Turno findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Turno.class, id);
        } finally {
            em.close();
        }
    }

    // Eliminar
    public void delete(Turno turno) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Turno mergedTurno = em.merge(turno);
            em.remove(mergedTurno);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    //Listar todas las personas asignadas a un turno
    public List<PersonaTurnoDTO> getPersonasByTurnoId(int turnoId) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT new TP2.punto1.DTO.PersonaTurnoDTO(p.id, p.nombre, p.anios) " +
                "FROM Turno t JOIN t.personasSet p WHERE t.id = :turnoId";
        TypedQuery<PersonaTurnoDTO> query = em.createQuery(jpql, PersonaTurnoDTO.class);
        query.setParameter("turnoId", turnoId);
        List<PersonaTurnoDTO> listadoPersonas = query.getResultList();
        for (PersonaTurnoDTO p : listadoPersonas) {
            System.out.println(p);
        }
        return listadoPersonas;

    }

//Personas asignadas a un turno marcando las que son socio
public List<PersonaTurnoDTO> findPersonasConSocioByTurnoId(int turnoId) {
    EntityManager em = emf.createEntityManager();
    String jpql = "SELECT new TP2.punto1.DTO.PersonaTurnoDTO(p.id, p.nombre, p.anios, " +
            "CASE WHEN s IS NOT NULL THEN true ELSE false END) " +
            "FROM Turno t JOIN t.personasSet p " +
            "LEFT JOIN TP2.punto1.entidades.Socio s ON p.id = s.persona.id " +
            "WHERE t.id = :turnoId";

    TypedQuery<PersonaTurnoDTO> query = em.createQuery(jpql, PersonaTurnoDTO.class);
    query.setParameter("turnoId", turnoId);
    List<PersonaTurnoDTO> PersonasSocio = query.getResultList();
    for (PersonaTurnoDTO ps : PersonasSocio) {
        System.out.println(ps);
    }
    return PersonasSocio;
}

    // Cerrar el EntityManagerFactory al finalizar
    public void close() {
        if (emf != null) {
            emf.close();
        }
    }
}

