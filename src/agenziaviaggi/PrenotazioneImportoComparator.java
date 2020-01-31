package agenziaviaggi;

import java.util.Comparator;

public class PrenotazioneImportoComparator implements Comparator<Prenotazione>{

	public int compare(Prenotazione a, Prenotazione b) {
		return (int)(a.getImporto()-b.getImporto());
	}

}
