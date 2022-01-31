package it.presentation;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import it.business.BancomatEjb;
import it.data.ContoCorrenteDAO;

@WebServlet("/conto")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public MyServlet() {
        super();
    }
    @EJB
    BancomatEjb bankService;
    


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContoCorrenteDAO ccDAO = new ContoCorrenteDAO();
		PrintWriter out = response.getWriter();
		
		bankService.setCcDAO(ccDAO);
		
		
		int numero = Integer.parseInt(request.getParameter("id"));
		float quantita = Float.parseFloat(request.getParameter("importo"));
		
		
		String tipo = request.getParameter("tipomovimento");
		
		if(!bankService.esisteCc(numero)) {
			System.out.println("Il conto è insesitente, operazione annullata");
			response.setContentType("text/html");
			out.println("<h1>Il conto è insesitente, operazione annullata</h1>");
			return;
		}		
		
		if(bankService.controllaOperazione(tipo, numero, quantita) != bankService.TUTTO_OK) {
			System.out.println("Operazione non consentita");
			return;
		}
		
		
		
		if(tipo.equalsIgnoreCase("preleva")) {			
						
			boolean risultato = bankService.preleva(numero, quantita);
			
			System.out.println("Prelievo completato!");
			if(risultato) {
				System.out.println("Prelievo completato");
				response.setContentType("text/html");
				out.println("<h1>Prelievo completato!</h1>");
				}else {
					System.out.println("Prelievo non riuscito");
					response.setContentType("text/html");
					out.println("<h1>Prelievo non riuscito!</h1>");
				}
			
		}else if(tipo.equalsIgnoreCase("deposita")) {
			boolean risultato = bankService.deposita(numero, quantita);			
			System.out.println("Deposito completato!");
			if(risultato) {
				System.out.println("Deposito completato");
				response.setContentType("text/html");
				out.println("<h1>Deposito completato!</h1>");
				
			}else {
				System.out.println("Deposito NON riuscito");
				response.setContentType("text/html");
				out.println("<h1>Deposito non riuscito</h1>");
			}
			
		}else if(tipo.equalsIgnoreCase("saldo")) {
			
			float saldo = bankService.saldo(numero);
			
			System.out.println("Il saldo è " + saldo);
		
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}