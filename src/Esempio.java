import java.util.List;

import agenziaviaggi.*;

public class Esempio {

	public static void main(String[] args) throws EccezioneClienteGiaEsistente, EccezionePraticaInesistente{

		Agenzia agenzia = new Agenzia();

		List<Cliente> clienti;
		List<Pratica> pratiche;
		List<Prenotazione> prenotazioni;
		
		System.out.println("Inserisco il cliente 'Rossi, Alberto, Piazza Leopardi 130, Milano'");
		Cliente c = agenzia.aggiungiCliente("Rossi", "Alberto", "Piazza Leopardi 130, Milano");
		
		System.out.println("Inserisco nuovamente il cliente 'Rossi, Alberto, Piazza Leopardi 130, Milano'");
		try
		{
			c = agenzia.aggiungiCliente("Rossi", "Alberto", "Piazza Leopardi 130, Milano");
		}
		catch (Exception e){
			System.out.println("Cliente già presente in anagrafica\n");
		}
		
		System.out.println("Inserisco altri tre clienti");
		agenzia.aggiungiCliente("Rossi", "Giuseppe", "Via Mazzini 20, Roma");
		agenzia.aggiungiCliente("Rossi", "Giuseppe", "Via Alessandro Magno 10, Torino");			
		agenzia.aggiungiCliente("Bianchi", "Luigi", "Strada Garibaldi 45, Napoli");			
		System.out.println("");
		
		System.out.println("Elenco clienti in anagrafica (ordinamento alfabetico crescente per cognome, nome e indirizzo):");
		
		clienti = (List<Cliente>) agenzia.elencoClienti();
		for(int i=0;i<clienti.size();i++){
			Cliente tempc = clienti.get(i);
			System.out.println(""+tempc.getCognome()+", "+tempc.getNome()+", "+tempc.getIndirizzo());
		}
		System.out.println("");
		
		System.out.println("Cerco cliente 'Bianchi, Luigi, Strada Garibaldi 45, Napoli'");
		c = agenzia.cercaCliente("Bianchi", "Luigi", "Strada Garibaldi 45, Napoli");
		if(c!=null)
			System.out.println("Cliente trovato in anagrafica\n");
		else
			System.out.println("Cliente non presente in anagrafica\n");
		
		System.out.println("Cerco cliente che contiene Magno");
		c = agenzia.cercaCliente("Magno");
		System.out.println(""+c.getCognome()+", "+c.getNome()+", "+c.getIndirizzo()+"\n");
		
		System.out.println("Aggiungo recapiti telefonici (uno duplicato) a 'Rossi, Alberto, Piazza Leopardi 130, Milano'");
		c.aggiungiContatto("+393381785256");
		c.aggiungiContatto("+39011564342");
		c.aggiungiContatto("+39011564342");
		
		System.out.println("Elenco recapiti aggiunti (nell'ordine di inserimento)");
		for(String s: c.elencoContatti())
			System.out.println(""+s);
		System.out.println("");
		
		System.out.println("Creo una nuova pratica di viaggio per 'Rossi, Alberto, Piazza Leopardi 130, Milano'");
		int idp = agenzia.nuovaPratica("Firenze con amici", "Rossi", "Alberto", "Piazza Leopardi 130, Milano");
		System.out.println("Creata pratica 'Firenze con amici' con identificativo "+idp+"\n");
		
		System.out.println("Cerco la pratica con identificativo 1000");
		Pratica pratica = agenzia.getPratica(1000);
		if(pratica!=null)
			System.out.println("Pratica trovata\n");
		else
			System.out.println("Pratica inesistente\n");
		
		System.out.println("Aggiungo tre prenotazioni alla pratica 1000\n");
		Prenotazione prenotazione = new PrenotazioneAlbergo("Hotel Genova, Firenze","20120924",3,185.00);
		pratica.aggiungiPrenotazione(prenotazione);
		pratica.aggiungiPrenotazione(new PrenotazioneAlbergo("Hotel Taormina, Firenze","20120927",2,632.50));
		pratica.aggiungiPrenotazione(new PrenotazioneVolo("CZ234","Venezia","Firenze", "20120923","20120929",99.50));
		
		System.out.println("Importo totale della pratica: "+pratica.getImportoTotale()+"\n");
		
		System.out.println("Creo una nuova pratica di viaggio per 'Rossi, Alberto, Piazza Leopardi 130, Milano'");
		idp=agenzia.nuovaPratica("Parigi a ottobre per lavoro", "Rossi", "Alberto", "Piazza Leopardi 130, Milano");
		System.out.println("Creata pratica 'Parigi a ottobre per lavoro' con identificativo "+idp+"\n");

		System.out.println("Aggiungo tre prenotazioni alla pratica 1001\n");
		pratica = agenzia.getPratica(1001);
		pratica.aggiungiPrenotazione(new PrenotazioneVolo("AF1329","Torino","Parigi", "20121012","20121013",225.10));
		pratica.aggiungiPrenotazione(new PrenotazioneAlbergo("Hotel de Vosges, Paris","20121012",1,143.00));
		System.out.println("Importo totale della pratica: "+pratica.getImportoTotale()+"\n");
		

		
		System.out.println("Creo una nuova pratica di viaggio per 'Bianchi, Luigi, Strada Garibaldi 45, Napoli'");
		idp = agenzia.nuovaPratica("Londra a capodanno", "Bianchi", "Luigi", "Strada Garibaldi 45, Napoli");
		System.out.println("Creata pratica 'Londra a capodanno' con identificativo "+idp+"\n");
		pratica = agenzia.getPratica(idp);
		pratica.aggiungiPrenotazione(new PrenotazioneAlbergo("Hotel Gatwick, London","20121231",1,423.00));
		
		System.out.println("Elenco delle pratiche di 'Rossi, Alberto, Piazza Leopardi 130, Milano' (ordinate per importo decrescente)");
		c = agenzia.cercaCliente("Rossi", "Alberto", "Piazza Leopardi 130, Milano");
		pratiche = (List<Pratica>)c.elencoPratiche();
		for(Pratica p:pratiche)
			System.out.println(""+p.getIdPratica()+" "+p.getDescrizione()+" "+p.getImportoTotale());
		System.out.println("");
		
		System.out.println("Elenco di tutte le pratiche di agenzia (ordinate per importo decrescente)");
		pratiche = (List<Pratica>)agenzia.elencoPratiche();
		for(Pratica p:pratiche)
			System.out.println(""+p.getIdPratica()+" "+p.getDescrizione()+" "+p.getImportoTotale());
		System.out.println("");
		
		System.out.println("Elenco delle prenotazioni della pratica 1000 (ordinamento per data crescente):");
		pratica = agenzia.getPratica(1000);
		prenotazioni = (List<Prenotazione>)pratica.elencoPrenotazioniPerData();
		
		for(Prenotazione tempp : prenotazioni){
			if(tempp instanceof PrenotazioneAlbergo)
				System.out.println("Prenotazione alberghiera, checkin "+((PrenotazioneAlbergo)tempp).getDataCheckIn()+", sistemazione "+((PrenotazioneAlbergo)tempp).getHotel());
			else if (tempp instanceof PrenotazioneVolo)
				System.out.println("Prenotazione volo aereo, partenza "+((PrenotazioneVolo)tempp).getDataPartenza()+", sigla "+((PrenotazioneVolo)tempp).getSigla());
		}
		System.out.println("");
			
		System.out.println("Elenco delle prenotazioni della pratica 1000 (ordinamento per importo crescente):");
		pratica = agenzia.getPratica(1000);
		prenotazioni = (List<Prenotazione>)pratica.elencoPrenotazioniPerImporto();
		
		for(Prenotazione tempp : prenotazioni){
			if(tempp instanceof PrenotazioneAlbergo)
				System.out.println("Prenotazione alberghiera, importo "+((PrenotazioneAlbergo)tempp).getImporto()+", sistemazione "+((PrenotazioneAlbergo)tempp).getHotel());
			else if (tempp instanceof PrenotazioneVolo)
				System.out.println("Prenotazione volo aereo, importo  "+((PrenotazioneVolo)tempp).getImporto()+", sigla "+((PrenotazioneVolo)tempp).getSigla());
		}
		
		System.out.println("");
			
		System.out.println("Importo complessivo per le prenotazioni dal 20120923 al 20120924: "+agenzia.calcolaImportoPerPeriodo("20120923","20120924")+"\n");
		
		System.out.println("Elenco dei clienti le cui pratiche superano l'importo totale di 500 euro (ordine alfabetico crescente):");
		
		clienti = (List<Cliente>) agenzia.elencoClientiSelezionati(500);
		for(int i=0;i<clienti.size();i++){
			Cliente tempc = clienti.get(i);
			System.out.println(""+tempc.getCognome()+", "+tempc.getNome()+", "+tempc.getIndirizzo());
		}
	}
}
