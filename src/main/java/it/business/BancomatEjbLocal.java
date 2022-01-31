package it.business;

import it.data.ContoCorrenteDAO;
import jakarta.ejb.Local;

@Local
public interface BancomatEjbLocal {
	public int controllaOperazione(String operazione, int numero, float quantita);
	public boolean preleva(int numero, float quantita);
	public boolean deposita(int numero, float quantita);
	public float saldo(int numero);
	

}
