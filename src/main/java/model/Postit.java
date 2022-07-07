package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "postit")
public class Postit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(nullable = false)
  private String titolo;
  @Column(nullable = false)
  private String testo;
  @Column(nullable = false)
  private LocalDate data_di_scadenza;
  @Column(nullable = false)
  private int priorita;
  private Boolean finito;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "codiceUtente")
  private Utente utente;
  
  public Postit() {}

  public long getId() {
	return id;
  }

  public void setId(long id) {
	this.id = id;
  }

  public String getTitolo() {
	return titolo;
  }

  public void setTitolo(String titolo) {
	this.titolo = titolo;
  }

  public String getTesto() {
	return testo;
  }

  public void setTesto(String testo) {
	this.testo = testo;
  }

  public LocalDate getDataDiScadenza() {
	return data_di_scadenza;
  }

  public void setDataDiScadenza(LocalDate data_di_scadenza) {
	this.data_di_scadenza = data_di_scadenza;
  }

  public int getPriorita() {
	return priorita;
  }

  public void setPriorita(int priorita) {
	this.priorita = priorita;
  }

  public Boolean getFinito() {
	return finito;
  }

  public void setFinito(Boolean finito) {
	this.finito = finito;
  }

  public Utente getUtente() {
	return utente;
  }

  public void setUtente(Utente utente) {
	this.utente = utente;
  }

  public Postit(long id, String titolo, String testo, LocalDate data_di_scadenza, int priorita, Boolean finito, Utente utente) {
	this.id = id;
	this.titolo = titolo;
	this.testo = testo;
	this.data_di_scadenza = data_di_scadenza;
	this.priorita = priorita;
	this.finito = finito;
	this.utente = utente;
  }

  public Postit(String titolo, String testo, LocalDate data_di_scadenza, int priorita, Boolean finito) {
	this.titolo = titolo;
	this.testo = testo;
	this.data_di_scadenza = data_di_scadenza;
	this.priorita = priorita;
	this.finito = finito;
  }

  public Postit(String titolo, String testo, LocalDate data_di_scadenza, int priorita, Boolean finito, Utente utente) {
	this.titolo = titolo;
	this.testo = testo;
	this.data_di_scadenza = data_di_scadenza;
	this.priorita = priorita;
	this.finito = finito;
	this.utente = utente;
  }

  @Override
  public String toString() {
	return "Postit [id=" + id + ", titolo=" + titolo + ", testo=" + testo + ", data_di_scadenza=" + data_di_scadenza
			+ ", priorita=" + priorita + ", finito=" + finito + "]";
  }
}