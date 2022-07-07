package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import dao.model.PostitDao;
import model.Postit;
import model.Utente;

public class PostitDaoJpa implements PostitDao{
  private static PostitDaoJpa instance = new PostitDaoJpa();
  
  private PostitDaoJpa() {}
  
  protected static PostitDaoJpa getInstance() {
	return instance;
  }

  @Override
  public boolean rimuoviToDo(Postit p) {
	EntityManager manager = JpaDaoFactory.getEntityManager();
	EntityTransaction transaction = manager.getTransaction();
    transaction.begin();
    
    try {
      manager.merge(p);
      transaction.commit();
      return true;
    } catch(Exception e) {
        transaction.rollback();
        return false;
    }
  }

  @Override
  public boolean updateToDo(Postit p) {
	EntityManager manager = JpaDaoFactory.getEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	transaction.begin();
		
	try {
	  manager.merge(p);
	  transaction.commit();
	  return true;
	} catch(Exception e) {
	    transaction.rollback();
		return false;
	}
  }

  @Override
  public Postit byIdPostIt(long idPostIt) {
	EntityManager manager = JpaDaoFactory.getEntityManager();
	Query query = manager.createQuery("select p from Postit p where p.id = ?1");
	query.setParameter(1, idPostIt);
	return (Postit)query.getSingleResult();
  }

  @Override
  public void cancellaPostIt(Postit p) {
    EntityManager manager = JpaDaoFactory.getEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	transaction.begin();
	p = manager.contains(p) ? p : manager.merge(p);
	manager.remove(p);
	transaction.commit();
  }
  
  @Override
  public boolean aggiungiToDo(Postit post) {
	EntityManager manager = JpaDaoFactory.getEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	transaction.begin();
	    
	try {
	  manager.merge(post);
	  transaction.commit();	
	  return true;
	} catch(Exception e) {
	    transaction.rollback();
	    return false;
	}
  }
  @Override
  public List<Postit> stampaToDoList(Utente u){
	EntityManager manager = JpaDaoFactory.getEntityManager();
    Query query = manager.createQuery("SELECT p FROM Postit p WHERE p.utente = ?1 AND p.finito = ?2 ORDER BY p.priorita DESC, p.data_di_scadenza ASC");
    query.setParameter(1, u);
    query.setParameter(2, 0);
    return query.getResultList();
  }

  @Override
  public List<Postit> stampaStorico(Utente u){
	EntityManager manager = JpaDaoFactory.getEntityManager();
	Query query = manager.createQuery("SELECT p FROM Postit p WHERE p.utente = ?1 ORDER BY p.priorita DESC, p.data_di_scadenza ASC");
	query.setParameter(1, u);
	return query.getResultList();
  } 
}