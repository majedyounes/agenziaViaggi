package agenziaviaggi;

public class PrenotazioneVolo extends Prenotazione{
	private String sigla;
	private String origine;
	private String destinazione;
	private String dataPartenza;
	private String dataArrivo;
	
	public PrenotazioneVolo(String sigla, String origine, String destinazione,
			String dataPartenza, String dataArrivo, double importo){
		super(importo);
		this.sigla = sigla;
		this.origine = origine;
		this.destinazione = destinazione;
		this.dataPartenza = dataPartenza;
		this.dataArrivo = dataArrivo;
	}
	
	public String getSigla(){
		return sigla;
	}
	
	public String getOrigine(){
		return origine;
	}
	
	public String getDestinazione(){
		return destinazione;
	}
	
	public String getDataPartenza(){
		return dataPartenza;
	}
	
	public String getDataArrivo(){
		return dataArrivo;
	}
}
