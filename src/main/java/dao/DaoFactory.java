package dao;

import dao.jpa.JpaDaoFactory;
import dao.model.PostitDao;
import dao.model.UtenteDao;

public abstract class DaoFactory {
  public abstract UtenteDao getUtenteDao();
  public abstract PostitDao getPostitDao();
  
  public static DaoFactory getDaoFactory() {
    return new JpaDaoFactory();
  }
}