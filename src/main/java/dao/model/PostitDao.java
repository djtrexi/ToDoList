package dao.model;

import java.util.List;

import model.Postit;
import model.Utente;

public interface PostitDao {
  public boolean rimuoviToDo(Postit p);
  public boolean updateToDo(Postit p);
  public Postit byIdPostIt(long idPostIt);
  public void cancellaPostIt(Postit p);
  public boolean aggiungiToDo(Postit post);
  public List<Postit> stampaToDoList(Utente u);
  public List<Postit> stampaStorico(Utente u);
}