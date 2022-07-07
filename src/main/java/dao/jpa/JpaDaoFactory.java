package dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.DaoFactory;
import dao.model.PostitDao;
import dao.model.UtenteDao;

public class JpaDaoFactory extends DaoFactory{

  @Override
  public UtenteDao getUtenteDao() {
	return UtenteDaoJpa.getInstance();
  }

  @Override
  public PostitDao getPostitDao() {
    return PostitDaoJpa.getInstance();
  }
  
  protected static EntityManager getEntityManager() {
    try {
	  Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
        e.printStackTrace();
	}
    return Persistence.createEntityManagerFactory("ToDoList").createEntityManager();
  }
}