package TP2.factory;

import TP2.punto1.DAO.DireccionDAO;
import TP2.punto1.DAO.PersonaDAO;
import TP2.punto1.DAO.SocioDAO;
import TP2.punto1.DAO.TurnoDAO;

public abstract class DAOfactory {
    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;

    public abstract PersonaDAO getPersonaDAO();
    public abstract DireccionDAO getDireccionDAO();
    public abstract SocioDAO getSocioDAO();
    public abstract TurnoDAO getTurnoDAO();


    public static DAOfactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC:
                return new MySQLDAOFactory();
            case DERBY_JDBC:
                return new DerbyDAOFactory();
            default:
                return null;
        }
    }
}
