package it.business;



import it.data.ContoCorrente;
import it.data.ContoCorrenteDAO;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

/**
 * Session Bean implementation class BancomatEjb
 */
@Stateless
@LocalBean
public class BancomatEjb implements BancomatEjbLocal {

    private ContoCorrenteDAO ccDAO;
    
   
    
    public static final int TUTTO_OK = 1;
	public static final int QTA_MINORE_0 = -1;
	public static final int  SALDO_INSUFF = -2;
	


	
	public int controllaOperazione(String operazione, int numero, float quantita) {
		if(quantita < 0) {
			return QTA_MINORE_0;
		}
		ContoCorrente contoControllo = ccDAO.getContoCorrente(numero);
		
		if(contoControllo.getSaldo() < quantita) {
			return SALDO_INSUFF;
		}
		return TUTTO_OK;
	}
			
	public boolean preleva(int numero, float quantita) {
		return ccDAO.preleva(numero, quantita);
		
	}
	
	public boolean deposita(int numero, float quantita) {
		return ccDAO.deposita(numero, quantita);
	}
	
	
	public float saldo(int numero) {
		return ccDAO.getContoCorrente(numero).getSaldo();
	}
	
	
	public boolean esisteCc(int numero) {
		return ccDAO.getContoCorrente(numero) != null;
	}
	
	
	public ContoCorrenteDAO getCcDAO() {
		return ccDAO;
	}

	public void setCcDAO(ContoCorrenteDAO ccDAO) {
		this.ccDAO = ccDAO;
	}
}

