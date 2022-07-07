package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "utente")
//@NamedQuery(name = "Utente.findAll", query = "SELECT u FROM Utente u")
public class Utente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(nullable = false)
  private String nome;
  @Column(nullable = false)
  private String cognome;
  @Column(nullable = false, unique = true)
  private String email;
  @Column(nullable = false)
  private String password;
  
  @OneToMany(mappedBy = "utente")
  private List<Postit> postit;
  
  public Utente() {}

  public long getId() {
	return id;
  }

  public void setId(long id) {
	this.id = id;
  }

  public String getNome() {
	return nome;
  }

  public void setNome(String nome) {
	this.nome = nome;
  }

  public String getCognome() {
	return cognome;
  }

  public void setCognome(String cognome) {
	this.cognome = cognome;
  }

  public String getEmail() {
	return email;
  }

  public void setEmail(String email) {
	this.email = email;
  }

  public String getPassword() {
	return password;
  }

  public void setPassword(String password) {
	this.password = password;
  }

  public List<Postit> getPostits() {
	return postit;
  }

  public void setPostits(List<Postit> postit) {
	this.postit = postit;
  }
  
  public void setTodoList(Postit p) {
    postit.add(p);
  }

  public Utente(long id, String nome, String cognome, String email, String password) {
	this.id = id;
	this.nome = nome;
	this.cognome = cognome;
	this.email = email;
	this.password = password;
  }

  public Utente(String nome, String cognome, String email, String password) {
	this.nome = nome;
	this.cognome = cognome;
	this.email = email;
	this.password = password;
  }
  
  @Override
  public String toString() {
    return "L'utente ID: " + getId() + " che si chiama " + getNome() + getCognome() + " e' collegato tramite l'email " + getEmail();
  }
}