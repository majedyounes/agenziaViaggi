package agenziaviaggi;

import java.util.Comparator;

public class PrenotazioneDataComparator implements Comparator<Prenotazione>{

	public int compare(Prenotazione a, Prenotazione b) {
		String dataa = null;
		String datab = null;
		
		if (a instanceof PrenotazioneAlbergo)
			dataa = ((PrenotazioneAlbergo) a).getDataCheckIn();
		if (a instanceof PrenotazioneVolo)
			dataa = ((PrenotazioneVolo) a).getDataPartenza();

		if (b instanceof PrenotazioneAlbergo)
			datab = ((PrenotazioneAlbergo) b).getDataCheckIn();
		if (b instanceof PrenotazioneVolo)
			datab = ((PrenotazioneVolo) b).getDataPartenza();
		
		return dataa.compareTo(datab);
	}

}
