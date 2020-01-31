package agenziaviaggi;

import java.util.Collection;
import java.util.*;

public class Cliente implements Comparable<Cliente>{
	private String cognome;
	private String nome;
	private String indirizzo;
	private List<String> contatti = new LinkedList<String>();
	private Map<Integer, Pratica> pratiche = new HashMap<Integer, Pratica>();

	public Cliente(String cognome, String nome, String indirizzo) {
		this.cognome = cognome;
		this.nome =nome;
		this.indirizzo = indirizzo;
	}

	public String getCognome(){
		return cognome;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getIndirizzo(){
		return indirizzo;
	}

	public void aggiungiContatto(String contatto){
		boolean trovato = false;
		if (contatto!=null)
			for (String s : contatti){
				if (s.compareTo(contatto)==0)
					trovato = true;
			}
		if (!trovato)
			contatti.add(contatto);
	}
	
	public void aggiungiPratica(Pratica pratica){
		pratiche.put(pratica.getIdPratica(), pratica);
	}

	public Collection<String> elencoContatti(){
		return contatti;
	}

	public Collection<Pratica> elencoPratiche(){
		LinkedList<Pratica> templ = new LinkedList<Pratica>(pratiche.values());
		Collections.sort(templ);
		return templ;
	}

	public int compareTo(Cliente altro) {
		if(this.cognome.compareTo(altro.getCognome())!=0)
			return this.cognome.compareTo(altro.getCognome());
		else if (this.nome.compareTo(altro.getNome())!=0)
			return this.nome.compareTo(altro.getNome());
		else
			return this.indirizzo.compareTo(altro.getIndirizzo());
	}

	public void eliminaPratica(int idPratica) {
			pratiche.remove(idPratica);
	}
}





