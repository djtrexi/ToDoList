package db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Postit;
import model.Utente;

public class Database {
  private static Database instance = new Database();
  
  private Database() {}

  public static Database getInstance() {
	return instance;
  }
}