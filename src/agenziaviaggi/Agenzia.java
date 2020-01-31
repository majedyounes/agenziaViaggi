package agenziaviaggi;

import java.util.Collection;
import java.util.*;

public class Agenzia {
	List<Cliente> clienti = new LinkedList<Cliente>();
	private int idPratica = 1000;
	Map<Integer, Pratica> pratiche = new HashMap<Integer, Pratica>();
	
	
	public Cliente aggiungiCliente(String cognome, String nome, String indirizzo) 
			throws EccezioneClienteGiaEsistente {
		boolean trovato = false;
		for (Cliente c : clienti){
			if (c.getNome().compareTo(nome)==0 && c.getCognome().compareTo(cognome)==0
					&& c.getIndirizzo().compareTo(indirizzo)==0)
				trovato = true;
		}
		
		if (trovato)
			throw new EccezioneClienteGiaEsistente();
		else{
			Cliente c = new Cliente(cognome, nome, indirizzo);
			clienti.add(c);
			return c;
		}
	}
	
	public Collection<Cliente> elencoClienti(){
		Collections.sort(clienti);
		return clienti;
	}
	
	public Cliente cercaCliente(String cognome, String nome, String indirizzo){
		for (Cliente c : clienti){
			if (c.getNome().compareTo(nome)==0 && c.getCognome().compareTo(cognome)==0
					&& c.getIndirizzo().compareTo(indirizzo)==0)
				return c;
		}
		return null;
	}
	
	public Cliente cercaCliente(String ricerca){
		Collections.sort(clienti);
		for (Cliente c : clienti)
			if (c.getCognome().contains(ricerca) || c.getNome().contains(ricerca) ||
					c.getIndirizzo().contains(ricerca))
				return c;
		return null;
	}
	
	public int nuovaPratica(String descrizione, String cognome, 
			String nome, String indirizzo){
		Cliente tempc = cercaCliente(cognome, nome, indirizzo);
		if (tempc == null){
			tempc = new Cliente (cognome, nome, indirizzo);
			clienti.add(tempc);
		}
		
		Pratica tempp = new Pratica (tempc, descrizione);
		tempp.setIdPratica(idPratica);
		pratiche.put(idPratica, tempp);
		tempc.aggiungiPratica(tempp);
		idPratica++;
		return tempp.getIdPratica();
	}
	
	public Pratica getPratica(int idPratica) throws EccezionePraticaInesistente{
		Pratica p = pratiche.get(idPratica);
		if (p == null)
			throw new EccezionePraticaInesistente();
		return p;
	}
	
	public void eliminaPratica(int idPratica) throws EccezionePraticaInesistente{
		if (!pratiche.containsKey(idPratica))
			throw new EccezionePraticaInesistente();
		Pratica p = pratiche.get(idPratica);
		System.err.println(p.getIdPratica());
		p.getCliente().eliminaPratica(idPratica);
//		System.err.println(p.getCliente().getNome());
//		System.err.println(idPratica);
		pratiche.remove(idPratica);
	}
	
	public Collection<Pratica> elencoPratiche(){
		LinkedList<Pratica> templ=
				new LinkedList<Pratica>(pratiche.values());
		Collections.sort(templ);
		return templ;
	}

	public double calcolaImportoPerPeriodo(String da, String a){
		String data ="";
		double totale = 0;
		for (Pratica p : pratiche.values()){
			for (Prenotazione pr : p.elencoPrenotazioniPerData()){
				if (pr instanceof PrenotazioneAlbergo){
					data = ((PrenotazioneAlbergo) pr).getDataCheckIn();
				}
				else if (pr instanceof PrenotazioneVolo){
					data = ((PrenotazioneVolo) pr).getDataPartenza();
				}
				if (data.compareTo(da)>=0 && data.compareTo(a)<=0)
					totale+=pr.getImporto();
			}
		}
		return totale;
	}
	
	public Collection<Cliente> elencoClientiSelezionati(double importo){
		List<Cliente> templ = new LinkedList<Cliente>();
		Collections.sort(clienti);
		for (Cliente c : clienti){
			double tot = 0;
			for (Pratica p : c.elencoPratiche())
				tot+=p.getImportoTotale();
			if (tot>importo)
				templ.add(c);
		}
		return templ;
	}
}
