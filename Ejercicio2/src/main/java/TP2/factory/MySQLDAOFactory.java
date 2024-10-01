package TP2.factory;
import TP2.punto1.DAO.DireccionDAO;
import TP2.punto1.DAO.PersonaDAO;
import TP2.punto1.DAO.SocioDAO;
import TP2.punto1.DAO.TurnoDAO;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MySQLDAOFactory extends DAOfactory {
    private EntityManagerFactory emf;

    public MySQLDAOFactory() {

        this.emf = Persistence.createEntityManagerFactory("mysql_persistence_unit");
    }


    public PersonaDAO getPersonaDAO() {

        return new PersonaDAO(emf);
    }

    public TurnoDAO getTurnoDAO() {
        return new TurnoDAO(emf);
    }


    public SocioDAO getSocioDAO() {
        return new SocioDAO(emf);
    }


    public DireccionDAO getDireccionDAO() {
        return new DireccionDAO(emf);
    }
}
