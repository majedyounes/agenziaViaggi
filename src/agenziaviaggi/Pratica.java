package agenziaviaggi;

import java.util.*;

public class Pratica implements Comparable<Pratica>{
	private Cliente cliente;
	private List<Prenotazione> prenotazioni = new LinkedList<Prenotazione>();
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	private int idPratica;
	private String descrizione;
	
	public Pratica (Cliente c, String descrizione){
		this.cliente = c; 
		this.descrizione = descrizione;
	}
	
	public int getIdPratica(){
		return idPratica;
	}
	
	public String getDescrizione(){
		return descrizione;
	}
	
	public void aggiungiPrenotazione(Prenotazione prenotazione){
		prenotazioni.add(prenotazione);
	}
	
	public double getImportoTotale(){
		double totale = 0;
		for (Prenotazione p:prenotazioni)
			totale += p.getImporto();
		return totale;
	}
	
	public Collection<Prenotazione> elencoPrenotazioniPerImporto()
	{
		Collections.sort(prenotazioni, new PrenotazioneImportoComparator());
		return prenotazioni;
	}

	public Collection<Prenotazione> elencoPrenotazioniPerData()
	{
		Collections.sort(prenotazioni, new PrenotazioneDataComparator());
		return prenotazioni;
	}

	public void setIdPratica(int idPratica) {
		this.idPratica = idPratica;
	}

	public int compareTo(Pratica altra) {
		return -(int) (this.getImportoTotale()-
				altra.getImportoTotale());
	}
}
