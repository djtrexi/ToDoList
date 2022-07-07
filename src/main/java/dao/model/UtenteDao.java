package dao.model;

import model.Utente;

public interface UtenteDao {
  public Utente byIdUtente(long idUtente);
  public Utente byEmailUtente(String email);
  public Utente login(String email, String password);
  public boolean registrazione(Utente utente);
}