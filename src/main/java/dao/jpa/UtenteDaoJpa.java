package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import dao.model.UtenteDao;
import model.Utente;

public class UtenteDaoJpa implements UtenteDao{
  private static UtenteDaoJpa instance = new UtenteDaoJpa();
  
  private UtenteDaoJpa() {}
  
  protected static UtenteDaoJpa getInstance() {
	return instance;
  }
	
  @Override
  public Utente byIdUtente(long idUtente) {
    EntityManager manager = JpaDaoFactory.getEntityManager();
	Query query = manager.createNativeQuery("SELECT * FROM utente u WHERE id = :idUtente");
	query.setParameter("idUtente", idUtente);
	return (Utente)query.getSingleResult();
  }
  
  @Override
  public Utente byEmailUtente(String email) {
	EntityManager manager = JpaDaoFactory.getEntityManager();
	Query query = manager.createQuery("select u from Utente u where u.email = ?1");
	query.setParameter(1, email);
	return (Utente)query.getSingleResult();
  }

  @Override
  public Utente login(String email, String password) {
	EntityManager manager = JpaDaoFactory.getEntityManager();
	Query query = manager.createQuery("SELECT u FROM Utente u WHERE u.email = ?1 AND u.password = ?2");
	query.setParameter(1, email);
	query.setParameter(2, password);
		
	List results = query.getResultList();
	if(!results.isEmpty()) {
	  return (Utente)query.getSingleResult();
	}
	else {
	  return null;
	}
  }

  @Override
  public boolean registrazione(Utente utente) {
	EntityManager manager = JpaDaoFactory.getEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	transaction.begin();
	    
	try {
	  manager.merge(utente);
	  transaction.commit();
	  return true;
	} catch(Exception e) {
	    transaction.rollback();
	    return false;
    }
  }
}